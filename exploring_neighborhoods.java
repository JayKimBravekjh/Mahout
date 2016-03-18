## Running an evaluation on the simple recommender 

DataModel model = new GroupLensDataModel (new File("ratings.dat"));
RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
RecommenderBuilder recommenderBuilder = new RecommenderBuilder(){
   @Override
   public Recommender buildRecommender{
        DataModel model)  throws TasteException{
      UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
      UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, model);
      return new GenericUserBasedRecommender(
        model, neighborhood, similarity);
  }
};
double score = evaluator.evaluate(
      recommenderBuilder, null, model, 0.95, 0.05);
System.out.println(score);


  
