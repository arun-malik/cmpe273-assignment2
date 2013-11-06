package edu.sjsu.cmpe.library.dto;

import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.activemq.transport.stomp.StompConnection;

public class StompDto {

	public static  String stompQueueName;
	public static  String stompTopicName;
	public static  String topicSelfImpName;
	public static  String topicMngImpName;
	public static  String topicComputerImpName;
	public static  String apolloUser;
	public static  String apolloPassword;
	public static  String apolloHost;
	public static  int apolloPort;
	private static StompConnection stompConnection;


	public static void createApolloConnection()
	{
		stompConnection = new StompConnection();
	}

	public static void sendMessage(String message) throws Exception
	{

		try {

			createApolloConnection();
			openApolloConnection();
			connectApolloBroker();
			stompConnection.begin("Transaction");
			stompConnection.send(stompQueueName, message  );
			stompConnection.commit("Transaction"); 
			disconectApolloConnection();


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
				closeApolloConnection();
				stompConnection = null;
			}
		}


	}

	private static void connectApolloBroker() throws Exception {
		stompConnection.connect(apolloUser,apolloPassword);

	}

	private static void openApolloConnection() throws UnknownHostException, IOException {
		stompConnection.open(apolloHost, apolloPort);

	}

	private static void closeApolloConnection() throws Exception {
		stompConnection.close();

	}

	private static void disconectApolloConnection() throws Exception {
		stompConnection.disconnect();

	}
}
