package edu.sjsu.cmpe.library.dto;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Maps;
import edu.sjsu.cmpe.library.domain.*;


@JsonPropertyOrder(alphabetic = true)
public class BooksDto extends LinksDto {
	
	private final static Map<Integer, Book> libraryBooks = Maps.newLinkedHashMap();
  
    public BooksDto() {
	super();
    }

    /** Get All List of Books
     * @return list of books
     */
    public static List<Book> getBooks() {
	     List<Book> bookList = new ArrayList<Book>(libraryBooks.values());
	     return bookList;
    }

    /**
     * Add Book to the bookList
     * @param book
     */
    public static Book addBookToStorage(final Book book) {
        libraryBooks.put(book.getIsbn(), book);
        return book;
    }
    
    /** Get Books by ISBN
     * @return list of books
     */
    public static Book getBookByISBN(int ISBNKey) {
	     return libraryBooks.get(ISBNKey);
    }

    /** Delete Books by ISBN
     * @return list of books
     */
    public static void deleteBookByISBN(int ISBNKey) {
	      libraryBooks.remove(ISBNKey);
    }

	public static Book updateBookByISBN(int ISBNKey, String updatedBookStatus) {
		
		Book toBeupdatedBook = libraryBooks.get(ISBNKey);
		toBeupdatedBook.seteBookStatus(updatedBookStatus);
		return toBeupdatedBook;
	}

	public static List<Review> getReviews(int ISBNKey) {
		return libraryBooks.get(ISBNKey).getBookRating();
	}
	
	public static Review getReviewsByISBN(int ISBNKey, int reviewId) {
		List<Review> listReviews = libraryBooks.get(ISBNKey).getBookRating();
		for (Review rvw : listReviews) {
	        if (rvw.getId() == reviewId) {
	            return rvw;
	        }
	    }
		return null;
	}
	
	public static List<Author> getAuthors(int ISBNKey) {
		return libraryBooks.get(ISBNKey).getAuthors();
	}
	
	public static Author getAuthorsByISBN(int ISBNKey, int authorId) {
		List<Author> listAuth = libraryBooks.get(ISBNKey).getAuthors();
		for (Author auth : listAuth) {
	        if (auth.getAuthorId() == authorId) {
	            return auth;
	        }
	    }
		return null;
	}
	
	public static Review createReviews(int ISBNKey,Review rating ) {
		
		libraryBooks.get(ISBNKey).addBookReview(rating);
		return rating;
	}
	
	public static void addInitialData() {
			
			Author auth = new Author();
			auth.setName("BookAuth1");
			
			
		
		 	Book book = new Book();
	        book.setCategory("computer");
	        book.setTitle("Java Concurrency in Practice");
            book.setCoverImage("http://goo.gl/N96GJN");
            book.seteBookStatus("available");
            book.addBookAuthor(auth);
            book.setNumberOfPages(100);
            book.setPublicationDate("10/06/2013");
            book.setLanguage("english");
	        libraryBooks.put(book.getIsbn(), book);

	        book = new Book();
	        book.setCategory("computer");
	        book.setTitle("Restful Web Services");
	        book.setCoverImage("http://goo.gl/ZGmzoJ");
	        book.seteBookStatus("available");
	        book.setPublicationDate("10/06/2013");
	        book.setNumberOfPages(100);
	        book.addBookAuthor(auth);
	        book.setLanguage("english");
	        libraryBooks.put(book.getIsbn(), book);
	        
		}
	
   
   
}