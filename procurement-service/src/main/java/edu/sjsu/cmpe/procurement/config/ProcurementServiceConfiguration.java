package edu.sjsu.cmpe.procurement.config;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class ProcurementServiceConfiguration extends Configuration {
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
	    private  String studentID;
	    
	    @NotEmpty
	    @JsonProperty
	    private String webResourcePostUrl;
	    
	    @NotEmpty
	    @JsonProperty
	    private String weResourceGetUrl;
	    
	    

		public String getWeResourceGetUrl() {
			return weResourceGetUrl;
		}

		public void setWeResourceGetUrl(String weResourceGetUrl) {
			this.weResourceGetUrl = weResourceGetUrl;
		}

		public String getWebResourcePostUrl() {
			return webResourcePostUrl;
		}

		public void setWebResourcePostUrl(String webResource) {
			this.webResourcePostUrl = webResource;
		}

		public String getStudentID() {
			return studentID;
		}

		public void setStudentID(String studentID) {
			this.studentID = studentID;
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
}
