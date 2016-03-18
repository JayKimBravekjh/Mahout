# revisiting a simple user-based recommender system. 

DataModel model = new FileDataModel(new File("intro.csv"));
UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
UserNeighborhood neighborhood = new NearestNUserNeighborhood (2, similarity, model);
Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 

