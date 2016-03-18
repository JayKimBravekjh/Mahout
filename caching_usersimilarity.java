# employing caching with a usersimilarity implementation 

UserSimilarity similarity = new CachingUserSimilarity(
    new SpearmanCorrelationSimilarity(model), model); 
    
    
