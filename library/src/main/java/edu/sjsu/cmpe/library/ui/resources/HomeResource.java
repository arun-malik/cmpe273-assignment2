package edu.sjsu.cmpe.library.ui.resources;

import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.library.api.resources.BookResource;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BooksDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
//import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;
import edu.sjsu.cmpe.library.ui.views.HomeView;

@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
    
    public HomeResource(
     ) {
      //  this.bookRepository = bookRepository;
    }

    @GET
    public HomeView getHome() {

    			BooksDto booksListResponse = new BooksDto();
    			try{
    				for(Iterator<Book> i = BooksDto.getBooks().iterator(); i.hasNext(); ) {
    					Book iBook = i.next();
    					booksListResponse.addLink(new LinkDto("view-book", "/books/" + iBook.getIsbn(),
    							"GET"));
    					booksListResponse.addLink(new LinkDto("update-book",
    							"/books/" + iBook.getIsbn(), "PUT"));
    					booksListResponse.addLink(new LinkDto("delete-book",
    							"/books/" + iBook.getIsbn(), "DELETE"));
    					booksListResponse.addLink(new LinkDto("create-review",
    							"/books/" + iBook.getIsbn() + "/reviews", "POST"));
    				}

    				HomeView homeView = new HomeView(booksListResponse);
    				return homeView; 
    			
		    }
			catch(Exception ex)
			{
				return new HomeView("Exception in fetching Books from Server : " + ex.getMessage() );
			}
			finally
			{
				if(booksListResponse != null)
				{
					booksListResponse = null;
				}
			}
    }
    
}
