package edu.sjsu.cmpe.library.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Book {

	/**
	 * Private Properties
	 **/
	private static int bookKey;
	private int isbn;
	
	@NotEmpty(message ="title is required field.")
	private String title;
	
	@NotNull(message ="publicaiton date is required field.")
	private Date publicationDate;
	
	private String language;
	private String coverImage;
	private String category;
	private int numberOfPages;
	private BookStatus eBookStatus;
	private List<Author> authors;
	private List<Review> bookReview;
	
	@SuppressWarnings("serial")
	public static final Map<String, BookStatus> BookStatusMapper = Collections
            .unmodifiableMap(new HashMap<String, BookStatus>() {
                { 
                    put("available", BookStatus.Available);
                    put("checked-out", BookStatus.CheckedOut);
                    put("in-queue", BookStatus.InQueue);
                    put("lost", BookStatus.Lost);
                }
            });
	
	public Book() {
		this.isbn = ++bookKey;
	}
	
	public Book(int isbn) {
		this.isbn = isbn;
	}
	 	
	/**
	 * Enum for Status
	 * 
	 * @author arunmalik
	 * 
	 */
	public enum BookStatus {
		Available, CheckedOut, InQueue, Lost
	}

	/**
	 * Properties
	 */

	/**
	 * ISBN get set
	 * 
	 * @return
	 */
	public int getIsbn() {
		return isbn;
	}
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	
	
	

	/**
	 * Title Get Set
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title  = checkNotNull(title, "title is null");;
	}

	/**
	 * Publication Date Get-Set
	 * 
	 * @return publicationDate
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	@JsonProperty("publication-date")
	public String getPublicationDate() {
		return dateFormat.format(publicationDate);
	}
	public void setPublicationDate(String publicationDate) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.publicationDate = dateFormat.parse(publicationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	/**
	 * language Get Set
	 * 
	 * @return
	 */
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * numberOfPages Get Set
	 * 
	 * @return
	 */
	@JsonProperty("num-pages")
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	
	/**
	 * Book Status Get Set
	 * @return
	 */
	@JsonProperty("status")
	public BookStatus geteBookStatus() {
		return eBookStatus;
	}

	public void seteBookStatus(String eBookStatus) {
		if(eBookStatus ==null)
		{
			this.eBookStatus = BookStatusMapper.get("available");
		}
		else
		{
			this.eBookStatus = BookStatusMapper.get(eBookStatus);
		}
	}

	/**
	 * List Of Authors Get Set
	 * @return
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	
	public void addBookAuthor(Author author) {
		
		if(this.authors ==null)
		{
			authors = new ArrayList<Author>();
		}
		this.authors.add(author);
	}
	
	public void addBookAuthors(List<Author> authors)
	{
		for(Author item:  authors)
		{
			this.authors.add(item);
		}
	}

	
	/**
	 * Review Get Set
	 * @return
	 */
	@JsonProperty("reviews")
	public List<Review> getBookRating() {
		return bookReview;
	}

//	public void setBookRaing(List<Review> bookRating) {
//		this.bookRating = bookRating;
//	}
	
	public void addBookReview(Review bookRating) {
		if(this.bookReview ==null)
		{
			bookReview = new ArrayList<Review>();
		}
		this.bookReview.add(bookRating);
	}
	
	public void addBookReviews(List<Review> bookReviews)
	{
		for(Review item:  bookReviews)
		{
			this.bookReview.add(item);
		}
	}


	/**
	 * @return the coverImage
	 */
	public String getCoverImage() {
		return coverImage;
	}


	/**
	 * @param coverImage the coverImage to set
	 */
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}





	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}





	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
}


