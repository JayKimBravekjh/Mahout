# evaluating precision and recall with Boolean data
DataModel model = new GeneraicBooleanPrefDataModel(
     new FileDataModel(new File("ua.base")));
     
RecommenderIRStatsEvaluator evaluator = 
    new GenericRecommenderIRStatsEvaluator();

RecommenderBuilder buildRecommender(DataModel model){
    @Override
    public Recommender buildRecommenderBuilder(DataModel model){
        UserSimilarity similarity = new LogLikelihoodSimilarity(model);
        UserNeighborhood neighborhood = 
            new NearestNUserNeighborhood(10, similarity, model);
        return new GenericUserBasedRecommender(model, neighborhood, similarity);
    ) 
); 
DataModelBuilder modelBuilder = new DataModelBuilder() { 
    @Override
    public DataModel buildDataModel(
        FastByIDMap<PreferenceArray> trainingData) { 
      return new GenericBooleanPreDataModel(
        GenericBooleanPrefDataModel.toDataMap(trainingData));
      }
};
IRStatistics stats = evaluator.evaluate(
    recommenderBuilder, modelBuilder, model, null, 10, 
    GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);

System.out.println(stats.getPrecision());
System.out.println(stats.getRecall());

