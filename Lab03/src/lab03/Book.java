package lab03;

//import lab02.Matrix;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 */
public class Book {

  private long isbn;
  private String author;
  private String title;

  public Book(long isbn, String author, String title) {
    this.isbn = isbn;
    this.author = author;
    this.title = title;
  }

  // return the author
  public String getAuthor() {
    return author;
  }

  // return the ISBN
  public long getIsbn() {
    return isbn;
  }

  // return the title
  public String getTitle() {
    return title;
  }

  /**
   * Two books are considered equal if they have the same ISBN, author, and
   * title.
   * 
   * @param other --
   *          the object begin compared with "this"
   * @return true if "other" is a Book and is equal to "this", false otherwise
   */
  public boolean equals(Object other) {
    //Lab Part 1
	  if (!(other instanceof Book)) return false;
      Book otherBook = (Book) other;
      return this.isbn == otherBook.isbn && 
             this.author.equals(otherBook.author) && 
             this.title.equals(otherBook.title);
	
  }
  
  /**
   * Returns a string representation of the book.
   */
  public String toString() {
    return isbn + ", " + author + ", \"" + title + "\"";
  }

	
}
