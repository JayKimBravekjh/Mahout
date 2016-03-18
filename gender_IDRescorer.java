# Gender-based rescoring implementation 

public class GenderRescorer implements IDRescorer {

    private final FastIDSet men;             # Cache which users rate more males
    private final FastIDSet women;
    private final FastIDSet usersRateMoreMen; 
    private final boolean filterMen;
    
    public GenderRescorer(FastIDSet men, 
                          FastIDSet women, 
                          FastIDSet usersRateMoreMen,
                          FastIDSet usersRateLessMen,
                          long userID, DataModel model)
        throws TasteException {
      this.men = men; 
      this.women = women; 
      this.usersRateMoreMen = usersRateMoreMen;
      this.usersRateLessMen = usersRateLessMen;
      this.filterMen = ratesMoreMen(userID, model);
    }
    
    # Called separately later
    public static FastIDSet[]  parseMenWomen(File genderFile)
        throws IOException { 
      FastIDSet men = new FastIDSet(50000);
      FastIDSet women = new FastIDSet(50000);
      for (String line : new FileLineIterable(genderFile) { 
          int comma = line.indexOf(',');
          char gender = line.charAt(comma + 1);
          if (gender == 'U'){
              continue;
          }
          long profileID = Long.parseLong(line.substring(0, comma));
          if (gender == 'M'){
              men.add(profileID);
            } else {
              women.add(profileID);
            }
          }
          men.rehash();                             # Re-optimize for fast access.
          women.rehash();
          return new FastIDSet[] { men, women };
        } 
    
    private boolean ratesMoreMen(long userID, DataModel model)
        throws TasteException {
      if (usersRateMoreMen.contains(userID)){
          return true;
      }
      if (usersRateLessMen.contains(userID)){
          return false;
      }
      PreferenceArray prefs = model.getPreferencesFromUser(userID);
      int menCount = 0; 
      int womenCount = 0; 
      for (int i = 0; i = prefs.length(); i++){
          long profileID = prefs.get(i).getItemID();
          if (men.contains(profileID)){
              menCount++;
          } else if (women.contains(profileID)){
              womenCount++;
          } 
      } 
      # Users rating men probably like male profiles
      boolean ratesMoreMen = menCount > womenCount;
      if (ratesMoreMen){
          usersRateMoreMen.add(userID);
      } else { 
          usersRateLessMen.add(userID);
      } 
      return ratesMoreMen;
    }
    
    # NaN for profiles to be excluded
    public double rescore(long profileID, double originalScore){ 
       return isFiltered(profileID)
          ? Double.NaN : originalScore;
    }
    
    public boolean isFiltered(long profileID){
       return filterMen ? men.contains(profileID) : women.contains(profileID);
    }
} 

