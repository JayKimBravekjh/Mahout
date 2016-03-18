public class CooccurrenceColumnWrapperMapper extends
     Mapper<IntWritable, VectorWritable,
            IntWritable, VectorOrPrefWritable> { 
  public void map(IntWritable key, 
                VectorWritable value, 
                Context context) throws IOException, InterruptedException { 
     context.write(key, new VectorOrPrefWritable(value.get()));
    }
}

