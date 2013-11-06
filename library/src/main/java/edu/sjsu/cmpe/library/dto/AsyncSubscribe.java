package edu.sjsu.cmpe.library.dto;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.fusesource.stomp.jms.StompJmsDestination;

import edu.sjsu.cmpe.library.domain.Book;

public class AsyncSubscribe implements MessageListener {

	public void subscribeTopic()
	{
		try {
			StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
			factory.setBrokerURI("tcp://"+StompDto.apolloHost + ":" + StompDto.apolloPort);
			
			Connection connection = factory.createConnection(StompDto.apolloUser, StompDto.apolloPassword);
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = new StompJmsDestination(StompDto.stompTopicName);

			MessageConsumer consumer = session.createConsumer(dest);
			AsyncSubscribe async = new AsyncSubscribe();
			consumer.setMessageListener(async);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("*******************************************************");
			System.out.println("received: " + msg.getText());
			
			updateBooksInLibrary(msg.getText());
			
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}
	
	private void updateBooksInLibrary(String message) {
		String[] values = message.split(":");
		int isbn = Integer.valueOf(values[0]);
		
		if(BooksDto.getBookByISBN(isbn) != null)
		{
			BooksDto.updateBookByISBN(isbn, "available");
			System.out.println("Book updated in store: \n  \t Isbn:"+isbn);
			
		}
		else
		{
			Book newBook = new Book(isbn);
			String title = values[1].substring(1,values[1].length()-1);
			String category = values[2].substring(1,values[2].length()-1);
			String coverImage = values[3].substring(1,values[3].length())  +":"+ values[4].substring(0, values[4].length()-1);
			newBook.setTitle(title);
			newBook.setCategory(category);
			newBook.setCoverImage(coverImage);
			BooksDto.addBookToStorage(newBook);
			System.out.println("Book added to store: \n  \t Isbn:"+newBook.getIsbn()+"  "+"\n\t title:"+title+"\n\t category:"+category+"\n\t coverimage:"+coverImage);
			
		 	
		}
	}
	
}
