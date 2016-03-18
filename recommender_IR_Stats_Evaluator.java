# Configuring and running a precision and recall evaluation 

RandomUtils.useTestSeed();
DataModel model = new FileDataModel (new File("intro.csv"));

RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator (); 

RecommenderBuilder recommenderBuilder = new RecommenderBuilder(){
    @Override
    public Recommender buildRecommender(DataModel model)
        throws TasteException(
      UserSimilarity similarity = new PearsonCorrelationSimilarity (model); 
      UserNeighborhood neighborhood = new NearestNUserNeighborhood (2, similarity, model);
      
      return new GenericUserBasedRecommender (model, neighborhood, similarity);
  )
);
# Evaluate precision and recall at 2
IRStatistics stats = evaluator.evaluate(
    recommenderBuilder, null, model, null, 2, 
    GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);

System.out.println(stats.getPrecision());
System.out.println(stats.getRecall());
