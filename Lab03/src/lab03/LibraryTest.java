package lab03;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * You will need to write more tests than the ones we provided
 */
public class LibraryTest {

  public static void main(String[] args) {
	  
	    // test an empty library
	    Library lib = new Library();

	    if (lib.lookup(978037429279L) != null)
	      System.err.println("TEST FAILED -- empty library: lookup(isbn)");
	    
	    ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
	    
	    if (booksCheckedOut == null || booksCheckedOut.size() != 0)
	      System.err.println("TEST FAILED -- empty library: lookup(holder)");
	    
	    if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
	      System.err.println("TEST FAILED -- empty library: checkout");
	    
	    if (lib.checkin(978037429279L))
	      System.err.println("TEST FAILED -- empty library: checkin(isbn)");
	    
	    if (lib.checkin("Jane Doe"))
	      System.err.println("TEST FAILED -- empty library: checkin(holder)");

	    // test a small library
	    lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
	    lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
	    lib.add(9780446580342L, "David Baldacci", "Simple Genius");
	    

	    if (lib.lookup(9780330351690L) != null)
	      System.err.println("TEST FAILED -- small library: lookup(isbn)");    
	    
	    if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
	      System.err.println("TEST FAILED -- small library: checkout");
	    
	    booksCheckedOut = lib.lookup("Jane Doe");
	        
	    if (booksCheckedOut == null
	        || booksCheckedOut.size() != 1
	        || !booksCheckedOut.get(0).equals(
	            new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
	        || !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
	        || !booksCheckedOut.get(0).getDueDate().equals(
	            new GregorianCalendar(2008, 1, 1))){
	      System.err.println("TEST FAILED -- small library: lookup(holder)");
	    }
	    if (!lib.checkin(9780330351690L))
	      System.err.println("TEST FAILED -- small library: checkin(isbn)");
	    if (lib.checkin("Jane Doe"))
	      System.err.println("TEST FAILED -- small library: checkin(holder)");

	 // ** Additional Tests Here **

        if (!lib.checkout(9780374292799L, "John Doe", 5, 5, 2022)) {
        	System.err.println("TEST FAILED -- small library: checkout multiple books");
        }
        // Test checking in all books by holder
        if (!lib. checkin("John Doe")) {
        	System. err.println("TEST FAILED -- small library: checkin by holder");
        }
        // Test trying to check out an already checked-out book
        lib.checkout(9780446580342L, "Alice",  2, 2, 2023);
        if (lib.checkout(9780446580342L, "Bob", 3, 3, 2023)) {
        	System.err.println("TEST FAILED -- already checked-out book: checkout");
        }
       // Test trying to check in an already checked-in book
       if (lib.checkin(9780330351690L)) {
    	   System.err.println("TEST FAILED -- already checked-in book: checkin");
       }
        
       System.out.println("Testing done.");
  	}

  }
