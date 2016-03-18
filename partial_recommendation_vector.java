## Computing partial recommendation vectors 
public class PartialMultiplyMapper extends 
    Mapper<IntWritable, VectorAndPrefsWritable, 
           VarLongWritable, VectorWritable> { 
  public void map(IntWritable key, 
                VectorAndPrefsWritable vectorAndPrefsWritable, 
                Context context) throws IOException, InterruptedException { 
                
    Vector cooccurrenceColumn = vectorAndPrefsWritable.getVector();
    List<Long> userIDs = vectorAndPrefsWritable.getUserIDs();
    List<Float> prefValues = vectorAndPrefsWritable.getValues();
    
    for (int i = 0; i < userIDs.size(); i++){
       long userID = userIDs.get(i);
       float prefValue = prefValues.get(i); 
       Vector partialProduct = cooccurrenceColumn.times(prefValue);
       context.write(new VarLongWritable(userID), 
                     new VectorWritable(partialProduct));
              }
        }
   }
   
   
    
    
    
