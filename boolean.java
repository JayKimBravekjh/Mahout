## Creating and evaluating with Boolean data 

DataModel model = new GenericBooleanPrefDataModel(
   GenericBooleanPrefDataModel.toDataMap(
      new FileDataModel (new File("ua.base")))); 
# Use GenericBooleanPrefDataModel
RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

RecommenderBuilder recommenderBuilder = new RecommenderBuilder(){
   public Recommender buildRecommender(DataModel model)
      throws TasteException(
     UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
     UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);
     return new GenericUserBasedRecommender(model, neighborhood, similarity);
  )
);
# build a GenericBooleanPrefDataModel 

double score = evaluator.evaluate(recommenderBuilder, modelBuilder, model, 0.9, 1.0);
System.out.println(score);




