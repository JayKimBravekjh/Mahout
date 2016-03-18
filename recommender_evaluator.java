# Configuring and running an evaluation of a recommender 
#Generates repeatable results 
RandomUtils.useTestSeed();
DataModel model = new FileDataModel (new File(*intro.csv"));

RecommenderEvaluator evaluator = 
    new AverageAbsoluteDifferenceRecommenderEvaluator ();
# builds recommender in listing 2.2
RecommenderBuilder builder = new RecommenderBuilder() { 
    @Override 
    public Recommender buildRecommender(DataModel model)
        throws TasteException { 
        UserSimilarity similarity = new PearsonCorrelationSimilarity (model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood (2, similarity, model);
        return new GenericUserBasedRecommender (model, neighborhood, similarity);
      }
    };
    
# Trains with 70% of data; tests with 30% 
    double score = evaluator.evaluate(
        builder, null, model, 0.7, 1.0);
    System.out.print(score);

