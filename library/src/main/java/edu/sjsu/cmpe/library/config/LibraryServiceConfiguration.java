package edu.sjsu.cmpe.library.config;


import javax.validation.constraints.NotNull;

import org.apache.activemq.transport.stomp.StompConnection;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;


public class LibraryServiceConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private  String stompQueueName;

    @NotEmpty
    @JsonProperty
    private  String stompTopicName;
    
    @NotEmpty
    @JsonProperty
    private  String apolloUser;
    
    @NotEmpty
    @JsonProperty
    private  String apolloPassword;
	
    @NotEmpty
    @JsonProperty
    private   String apolloHost;
	
    @NotNull
    @JsonProperty
    private  int apolloPort;
	
    @NotEmpty
    @JsonProperty
    public  String topicSelfImpName;

    @NotEmpty
    @JsonProperty
    public  String topicMngImpName;

    @NotEmpty
    @JsonProperty
    public  String topicComputerImpName;
    
    @NotEmpty
    @JsonProperty
    public static String currentLibraryName;
    
    public  String getCurrentLibraryName() {
		return currentLibraryName;
	}

	public   void setCurrentLibraryName(String currentLibraryName) {
		this.currentLibraryName = currentLibraryName;
	}

	/**
     * @return the stompQueueName
     */
    public String getStompQueueName() {
        return stompQueueName;
    }

    /**
     * @param stompQueueName
     *            the stompQueueName to set
     */
    public void setStompQueueName(String stompQueueName) {
        this.stompQueueName = stompQueueName;
    }

    /**
     * @return the stompTopicName
     */
    public String getStompTopicName() {
        return stompTopicName;
    }

    /**
     * @param stompTopicName
     *            the stompTopicName to set
     */
    public void setStompTopicName(String stompTopicName) {
        this.stompTopicName = stompTopicName;
    }
    
    public String getApolloUser() {
		return apolloUser;
	}

	public void setApolloUser(String apolloUser) {
		this.apolloUser = apolloUser;
	}

	/**
	 * @return the apolloPassword
	 */
	public String getApolloPassword() {
		return apolloPassword;
	}

	/**
	 * @param apolloPassword the apolloPassword to set
	 */
	public void setApolloPassword(String apolloPassword) {
		this.apolloPassword = apolloPassword;
	}

	/**
	 * @return the apolloHost
	 */
	public String getApolloHost() {
		return apolloHost;
	}

	/**
	 * @param apolloHost the apolloHost to set
	 */
	public void setApolloHost(String apolloHost) {
		this.apolloHost = apolloHost;
	}

	/**
	 * @return the apolloPort
	 */
	public int getApolloPort() {
		return apolloPort;
	}

	/**
	 * @param apolloPort the apolloPort to set
	 */
	public void setApolloPort(int apolloPort) {
		this.apolloPort = apolloPort;
	}

	/**
	 * @return the topicSelfImpName
	 */
	public  String getTopicSelfImpName() {
		return topicSelfImpName;
	}

	/**
	 * @param topicSelfImpName the topicSelfImpName to set
	 */
	public  void setTopicSelfImpName(String topicSelfImpName) {
		this.topicSelfImpName = topicSelfImpName;
	}

	/**
	 * @return the topicMngImpName
	 */
	public  String getTopicMngImpName() {
		return topicMngImpName;
	}

	/**
	 * @param topicMngImpName the topicMngImpName to set
	 */
	public  void setTopicMngImpName(String topicMngImpName) {
		this.topicMngImpName = topicMngImpName;
	}

	/**
	 * @return the topicComputerImpName
	 */
	public  String getTopicComputerImpName() {
		return topicComputerImpName;
	}

	/**
	 * @param topicComputerImpName the topicComputerImpName to set
	 */
	public  void setTopicComputerImpName(String topicComputerImpName) {
		this.topicComputerImpName = topicComputerImpName;
	}
    
}
