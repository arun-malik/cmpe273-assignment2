package edu.sjsu.cmpe.library.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BooksDto;

public class HomeView extends View {

    private static  String errorMsg ;
    private static BooksDto books;
    private static Book book;


    public HomeView() {
        super("home.mustache");
    }

    public HomeView(String errorMsg) {
        super("home.mustache");
        this.errorMsg = errorMsg;
    }
    
    public HomeView(BooksDto books) {
        super("home.mustache");
        this.books = books;
    }

    public List<Book> getBooks() {
        return books.getBooks();
    }

    public void setBooks(BooksDto books) {
        this.books = books;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}



}
