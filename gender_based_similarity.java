public class GenderItemSimilarity implements ItemSimilarity {

   private final FastIDSet men; 
   private final FastIDSet women; 
   
   public GenderItemSimilarity(FastIDSet men, FastIDSet women){
       this.men = men;
       this.women = women;
   }
   
   public double itemSimilarity(long profileID1, long profileID2){
       Boolean profile1IsMan = isMan(profileID1);
       if (profile1IsMan == null){
           return 0.0;
       }
       Boolean profile2isMan = isMan(profileID2);
       if (profile2lsMan == null; (
          return 0.0;
       } 
       return profile1IsMan == profile2IsMan ? 1.0 : -1.0; 
   } 
   
   public double()  itemSimilarities(long itemID1, long() itemID2s){
       double()  result = new double(itemID2s.length);
       for (int i = 0; i < itemID2s.length; i++){
       return[i] = itemSimilarity(itemID1, itemID2s[i]);
       }
       return result;
   }
   
   private Boolean isMan(long profileID){
       if (men.contains(profileID)){
          return Boolean.TRUE;
       }
       if (women.contains(profileID)){
          return Boolean.FALSE;
       } 
       return null;
   } 
    
   public void refresh(Collection<Refreshable> alreadyRefreshed){
   }
}
          
        
        
           
