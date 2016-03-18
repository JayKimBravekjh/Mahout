# updating listing 4.1 to use a custom DataModel for GroupLens

DataModel model = new GroupLensDataModel(new File("ratings.dat"));
UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, model);
Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 
LoadEvaluator.runLoad(recommender);
