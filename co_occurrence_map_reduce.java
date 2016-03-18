## Mapper component of co-occurence computation 

public class UserVectorToCooccurrenceMapper extends
      Mapper<VarLongWritable, VectorWritable, IntWritable, IntWritable> { 
  public void map(VarLongWritable userID, 
               VectorWritable userVector,
               Context context)
## Only iterate over nonzero elements 
        throws IOException, InterruptedException { 
      Iterator<Vector.Element> it = 
        userVector.get().iterateNonZero();
      while (it.hasNext()){
        int index1 = it.next().index();
        Iterator<Vector.Element> it2 = 
          userVector.get().iterateNonZero();
        while (it2.hasNext()){
          int index2 = it2.next().index();
          context.write(new IntWritable(index),
                        new IntWritable(index2));   ## Record count of I 
        }
    }  } 
    
    
## Reducer component of co-occurence computation 
public class UserVectorToCooccurrenceReducer extends
     Reducer<IntWritable, IntWritable, IntWritable, VectorWritable> { 
  public void reduce(IntWritable itemIndex1, 
               Iterable<IntWritable> itemIndex2s, 
               Context context)
      throws IOException, InterruptedException { 
    Vector cooccurrenceRow = 
      new RandomAccessSparseVector(Integer.MAX_VALUE, 100);
    for (IntWritable intWritable : itemIndex2s){
      int itemIndex2 = intWritable.get();
      cooccurrenceRow.set(
         itemIndex2, 
         cooccurrenceRow.get(itemIndex2) + 1.0);   ## Accumulate counts for items 1, 2
    }
## Record entire item I vector     
    context.write(
         itemIndex1, 
         new VectorWritable(cooccurrenceRow));
    } 
 } 
 
    
       
