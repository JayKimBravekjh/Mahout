public class AggregateCombiner extends
    Reducer<VarLongWritable, VectorWritable, 
            VarLongWritable, VectorWritable> {
  
  public void reduce(VarLongWritable key, 
                     Iterable<VectorWritable> values, 
                     Context context)
      throws IOException, InterruptedException { 
    Vector partial = null;
    for (VectorWritable vectorWritable : values){
      partial = partial == null ?
         vectorWritable.get() : partial.plus(vectorWritable.get());
    }
    context.write(key, new VectorWritable(partial));
  }
}

