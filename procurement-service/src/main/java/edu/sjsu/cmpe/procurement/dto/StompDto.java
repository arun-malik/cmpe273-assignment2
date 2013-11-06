package edu.sjsu.cmpe.procurement.dto;


import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicPublisher;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.broker.TransportConnection;
import org.apache.activemq.transport.stomp.Stomp.Headers.Subscribe;
import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.StompNIOTransport;
import org.apache.activemq.transport.stomp.StompTransportFactory;
import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.fusesource.stomp.jms.StompJmsDestination;

import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.spi.inject.ClientSide;


public class StompDto {

	public static  String stompQueueName;
	public static  String stompTopicName;
	public static  String apolloUser;
	public static  String apolloPassword;
	public static  String apolloHost;
	public static  int apolloPort;
	public static String studentID;
	private static StompConnection stompConnection;



	public static void createConnection()
	{
		try{
			stompConnection = new StompConnection();
		}
		catch(Exception ex)
		{

		}	
	}

	public static void sendMessage(String message) throws Exception
	{

		try {

			createConnection();
			openConnection();
			connectStompQueue();
			stompConnection.begin("Transaction");
			stompConnection.send(stompQueueName, message  );
			stompConnection.commit("Transaction");  
			disconectConnection();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(stompConnection != null)
			{
				closeConnection();
				stompConnection = null;
			}
		}


	}

	private static void connectStompQueue() {
		try
		{
			stompConnection.connect(apolloUser,apolloPassword);
		}
		catch(Exception ex)
		{

		}
	}

	private static void openConnection() {
		try
		{
			stompConnection.open(apolloHost, apolloPort);
		}
		catch(Exception ex)
		{

		}
	}

	private static void closeConnection()  {
		try
		{
			stompConnection.close();
		}
		catch(Exception ex)
		{

		}
	}

	private static void disconectConnection() throws Exception {
		stompConnection.disconnect();

	}

	public static List<String> getMessagefromQueue() 
	{
		List<String> msgfromQueue =  new ArrayList<String>();

		try {

			createConnection();
			openConnection();
			connectStompQueue();
			stompConnection.subscribe(stompQueueName, Subscribe.AckModeValues.CLIENT);
			stompConnection.begin("Transaction");
			while (true) {
				StompFrame message = null;
				try {
					message = stompConnection.receive(5000);
				} catch (Exception e) {
					break;
				}
				msgfromQueue.add(message.getBody());

				stompConnection.ack(message, "Transaction");
			}
			stompConnection.commit("Transaction");     
			disconectConnection();
			closeConnection();
			return msgfromQueue;
		}catch(Exception ex)
		{
			return msgfromQueue;	
		}
	}

	public static void publishBooksRToTopic(String msg,String cat) 
	{
		
		try {
			
			StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
			factory.setBrokerURI("tcp://" + apolloHost + ":" + apolloPort);
			Connection jmconnection = factory.createConnection(apolloUser, apolloPassword);
			
			jmconnection.start();
			Session session = jmconnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = new StompJmsDestination("/topic/04607.book."+cat);
			MessageProducer producer = session.createProducer(dest);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			if(msg=="SHUTDOWN")
			{ 
				System.out.println("sending shutdown msg"); 
				producer.send(session.createTextMessage("SHUTDOWN")); 
			}
			else { 
				 TextMessage message = session.createTextMessage(msg); 
				 message.setLongProperty("id", System.currentTimeMillis()); 
				 producer.send(message); System.out.println("successfully published"); 
				 }
			jmconnection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}