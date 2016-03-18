DiffStorage diffStorage = new MemoryDiffStorage(
    model, Weighting.WEIGHTED, 1000000L);

return new SlopeOneRecommender(
    model, Weighting.WEIGHTED, Weighting.WEIGHTED, diffStorage);
    
