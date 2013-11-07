package edu.sjsu.cmpe.library.domain;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import edu.sjsu.cmpe.library.dto.LinksDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Author extends LinksDto {
	
	private static int authorKey;
	private int id;
	
	//@NotEmpty(message ="author name is required field.")
	private String name;
	
	private List<Book> booksByAuthors;
	
	public Author() {
		this.id = ++authorKey;
	}
	
	
	public int getAuthorId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooksByAuthors() {
		return booksByAuthors;
	}
	public void setBooksByAuthors(List<Book> booksByAuthors) {
		this.booksByAuthors = booksByAuthors;
	}
}
