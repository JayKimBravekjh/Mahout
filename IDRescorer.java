# IDRescorer that omits out-of-stock books and boosts a genre

public class GenreRescorer implements IDRescorer {
    
    private final Genre currentGenre;
     
    public GenreRescorer(Genre currentGenre) { 
        this.currentGenre = CurrentGenre;
    } 
    
# Assume BookManager exists 
    public double rescore(long itemID, double originalScore){
        Book book = BookManager.lookupBook(itemID);
        if (book.getGenre().equals(currentGenre)){
            return originalScore * 1.2;                # Boost estimate by 20%
        } 
        return originalScore;                  # Don't change anything else 
    } 
    

    public boolean isFiltered(long itemID) { 
        Book book = BookManager.lookupBook(itemID);
        return book.isOutOfStock();         # Filter not-in-stock books 
      }
    } 
