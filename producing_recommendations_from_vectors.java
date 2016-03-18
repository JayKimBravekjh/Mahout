public class AggregateAndRecommendReducer extends 
     Reducer<VarLongWritable.VectorWritable, 
             VarLongWritable.RecommendedItemsWritable>{
   
  public void reduce(VarLongWritable key, 
                     Iterable<VectorWritable> values,
                     Context context)
       throws IOException, InterruptedException {
     
     Vector recommendationVector = null;
     for (VectorWritable vectorWritable : values){
         recommendationVector = recommendationVector == null ?   # Build recommendation vector by summing
             vectorWritable.get() : 
             
     recommendationVector.plus(vectorWritable.get());
     } 
     
     Queue<RecommendedItem> topItems = new PriorityQueue<RecommendedItem>{
         recommendationsPerUser + 1,
         Collections.reverseOrder(
             ByValueRecommendedItemComparator.getInstance()));
             
     Iterator<Vector.Element> recommendationVectorIterator = 
         recommendationVector.iterateNonZero();
     while (recommendationVectorIterator.hasNext()){
        Vector.Element element = recommendationVectorIterator.next();
        int index = element.index();
        float value = (float) element.get();
        if (topItems.add(new GenericRecommendedItem(
                             indexItemIDMap.get(index), value));
        { else if (value > topItems.peek().getValue()){      ## Find top N highest values 
          topItems.add(new GenericRecommendedItem(
                            indexItemIDMap.get(index), value));
          topItems.poll();
        }
      }
     List<RecommendedItem> recommendations = 
        new ArrayList<RecommendedItem>(topItems.size());
     recommendations.addAll(topItems);
     Collections.sort(recommendations, 
                      ByValueRecommendedItemComparator.getInstance());
    
    # Output recommendations in order
     context.write(
         key, 
         new
      RecommendedItemsWritable(recommendations));
   }
} 


         
         
         
