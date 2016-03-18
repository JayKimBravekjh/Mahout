# Triggering a refresh of a recommender system 

DataModel dataModel = new FileDataModel(new File("input.csv");
Recommender recommender = new SlopeOneRecommender(dataModel);

# Refresh DataModel, then itself
recommender.refresh(null);
