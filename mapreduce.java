# a mapper that parses Wikipedia link files 

public class WikipediaToItemPrefsMapper
     extends Mapper<LongWritable, Text, VarLongWritable, VarLongWritable>  { 
     
  private static final Pattern NUMBERS = Pattern.compile("(\\d+)"); 
  
  public void map(LongWritable key, 
                Text value, 
                Context context)
      throws IOException, InterruptedException { 
    String line = value.toString();
    Matcher m = NUMBERS.matcher(line);

# Locate user ID
    m.find();
    VarLongWritable userID = 
        new VarLongWritable(Long.parseLong(m.group()));
    VarLongWritable itemID = new VarLongWritable();

# Emit user-item pair for each item ID
    while (m.find()){
        itemID.set(Long.parseLong(m.group()));
        context.write(userID, itemID);
    }
  }
}
    
# reducer which produces Vectors from a user's item preferences
public class WikipediaToVectorReducer extends 
   Reducer<VarLongWritable, VarLongWritable, VarLongWritable, VectorWritable> { 
   public void reduce(VarLongWritable userID, 
                      Iterable<VarLongWritable> itemPrefs,
                      Context context)
        throws IOException, InterruptedException { 
      Vector userVector = new RandomAccessSparseVector (
         Integer.MAX_VALUE, 100);
# Iterate over item-preference pairs for user
      for (VarLongWritable itemPref : itemPrefs){
         userVector.set((int)itemPref.get(), 1.0f);
      }
# Set dimension "item ID" to item's preference value. 
      context.write(userID, new VectorWritable(userVector));
  }
}
