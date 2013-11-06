package edu.sjsu.cmpe.procurement.dto;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import edu.sjsu.cmpe.procurement.domain.LostInventory;

public class PublisherDto {

	public static String webResourcePostUrl;
	public static String webResourceGetUrl;
	private final String contentType = "application/json";
	Client jerseyClient;
	public PublisherDto()
	{
		jerseyClient = Client.create();
	}
	
	public void postPublisher(String input)
	{
		WebResource webResource = jerseyClient.resource(webResourcePostUrl);
		ClientResponse response = webResource.type(contentType).post(ClientResponse.class, input);
		LostInventoryDto.emptyInventory();
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus() + " " + response.getResponseDate());
		}
		System.out.println("Publisher's  Message .... : "+ response.getEntity(String.class) + "\n");
	}
	
	public void getPublisher()
	{
		WebResource webResource = jerseyClient.resource(webResourceGetUrl);
		//Genson Library
		LostInventory response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .get(LostInventory.class);
//		ClientResponse response = webResource.accept(contentType).get(ClientResponse.class);
//		GenericType<List<LostInventory>> genericType = new GenericType<List<LostInventory>>() {
//        };
//        List<LostInventory> data = new ArrayList<LostInventory>();
//        data = (response.getEntity(genericType));
        
//		if (response.getStatus() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ response.getStatus() + " " + response.getResponseDate());
//		}
//		else
//		{
		System.out.println("*******************************************************");
		System.out.println("Books Count.... : "+ response.getShipped_books().size());//response.getEntity(String.class) + "\n");
		
			for (LostInventory item : response.getShipped_books()) {
	            LostInventoryDto.setNewbooks(item);
	            System.out.println("*******************************************************");
				System.out.println("isbn: " + item.getBookISBN());
	            System.out.println("title : " + item.getTitle());
	            System.out.println("category : " + item.getCategory());
	            System.out.println("coverimage : " + item.getCoverimage());
	        }
//		}
		
		//return response;
	}
	
	
	
}
