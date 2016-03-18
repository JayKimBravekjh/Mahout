## A simple user-based recommender program with Mahout
class RecommenderIntro { 

    public static void main(String[]  args) throws Exception { 
      ## load data file. 
        DataModel model = 
            new FileDataModel  (new File("intro.csv")); 
        
        UserSimilarity similarity = 
            new PearsonCorrelationSimilarity (model);
        
        UserNeighborhood neighborhood = 
            new NearestNUserNeighborhood (2, similarity, model);
        
        Recommender recommender = new GenericUserBasedRecommender (
            model, neighborhood, similarity);
        
      ## Create recommender engine 
        List<RecommendedItem> recommendations = 
            recommender.recommend(1, 1);
      
      ## for user I, recommend I item. 
        for (RecommendedItem recommendation : recommendations) { 
            System.out.println(recommendation);
        }
      }
    }
      
      
