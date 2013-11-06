package edu.sjsu.cmpe.procurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import de.spinscale.dropwizard.jobs.JobsBundle;
import edu.sjsu.cmpe.procurement.dto.PublisherDto;
import edu.sjsu.cmpe.procurement.dto.StompDto;
import edu.sjsu.cmpe.procurement.config.ProcurementServiceConfiguration;

public class ProcurementService extends
		Service<ProcurementServiceConfiguration> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) throws Exception {
		new ProcurementService().run(args);
	}

	@Override
	public void initialize(Bootstrap<ProcurementServiceConfiguration> bootstrap) {
		bootstrap.setName("procurement-service");
	    bootstrap.addBundle(new JobsBundle("edu.sjsu.cmpe.procurement.domain"));
	   
	}

	@Override
	public void run(ProcurementServiceConfiguration configuration,
			Environment environment) throws Exception {

		// This is how you pull the configurations from library_x_config.yml
		StompDto.stompQueueName = configuration.getStompQueueName();
		StompDto.stompTopicName = configuration.getStompTopicName();
		StompDto.apolloUser = configuration.getApolloUser();
		StompDto.apolloPassword = configuration.getApolloPassword();
		StompDto.apolloHost = configuration.getApolloHost();
		StompDto.apolloPort = configuration.getApolloPort();
		StompDto.studentID = configuration.getStudentID();
		PublisherDto.webResourceGetUrl = configuration.getWeResourceGetUrl();
		PublisherDto.webResourcePostUrl = configuration.getWebResourcePostUrl();
		
		log.debug(configuration.getStudentID());
		log.debug(StompDto.studentID);
//		String message = "{library-a : 123}";
//		Pattern p = Pattern.compile("[a-zA-Z]*-*[a-zA-Z]*");
//		Matcher m = p.matcher(message);
//		while(m.find())
//		{
//		    System.out.println(m.group());
//		}
//		
		//StompDto.getMessagefromQueue();

		// log.debug(configuration.getStudentID());
	}
}
