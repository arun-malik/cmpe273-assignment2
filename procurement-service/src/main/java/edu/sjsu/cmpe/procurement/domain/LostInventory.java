package edu.sjsu.cmpe.procurement.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LostInventory {
	
	private String libraryName;
	
	@JsonProperty("isbn")
	private int bookISBN;
	private int lostInventoryKey;
	private String category;
	private String title;
	private String coverimage;
	private List<LostInventory> shipped_books;
	
	/**
	 * @return the libraryName
	 */
	public String getLibraryName() {
		return libraryName;
	}
	/**
	 * @param libraryName the libraryName to set
	 */
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	/**
	 * @return the bookISBN
	 */
	public int getBookISBN() {
		return bookISBN;
	}
	/**
	 * @param bookISBN the bookISBN to set
	 */
	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}
	/**
	 * @return the lostInventoryKey
	 */
	public int getLostInventoryKey() {
		return lostInventoryKey;
	}
	/**
	 * @param lostInventoryKey the lostInventoryKey to set
	 */
	public void setLostInventoryKey(int lostInventoryKey) {
		this.lostInventoryKey = lostInventoryKey;
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
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the coverimage
	 */
	public String getCoverimage() {
		return coverimage;
	}
	/**
	 * @param coverimage the coverimage to set
	 */
	public void setCoverimage(String coverimage) {
		this.coverimage = coverimage;
	}
	/**
	 * @return the shipped_books
	 */
	public List<LostInventory> getShipped_books() {
		return shipped_books;
	}
	/**
	 * @param shipped_books the shipped_books to set
	 */
	public void setShipped_books(List<LostInventory> shipped_books) {
		this.shipped_books = shipped_books;
	}
	
	
}
