	package edu.sjsu.cmpe.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

import edu.sjsu.cmpe.library.api.resources.BookResource;
import edu.sjsu.cmpe.library.api.resources.RootResource;
import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;
import edu.sjsu.cmpe.library.dto.AsyncSubscribe;
import edu.sjsu.cmpe.library.dto.BooksDto;
import edu.sjsu.cmpe.library.dto.StompDto;
import edu.sjsu.cmpe.library.ui.resources.HomeResource;


public class LibraryService extends Service<LibraryServiceConfiguration> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) throws Exception {
	new LibraryService().run(args);
    }

    @Override
    public void initialize(Bootstrap<LibraryServiceConfiguration> bootstrap) {
	bootstrap.setName("library-service");
    bootstrap.addBundle(new ViewBundle());
    bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(LibraryServiceConfiguration configuration,
	    Environment environment) throws Exception {

        // This is how you pull the configurations from library_x_config.yml
    	StompDto.stompQueueName = configuration.getStompQueueName();
    	StompDto.stompTopicName = configuration.getStompTopicName();
    	StompDto.apolloUser = configuration.getApolloUser();
    	StompDto.apolloPassword = configuration.getApolloPassword();
        StompDto.apolloHost = configuration.getApolloHost();
        StompDto.apolloPort = configuration.getApolloPort();
        StompDto.topicSelfImpName = configuration.getTopicSelfImpName();
        StompDto.topicMngImpName = configuration.getTopicMngImpName();
        StompDto.topicComputerImpName = configuration.getTopicComputerImpName();
        AsyncSubscribe obj = new AsyncSubscribe();
        obj.subscribeTopic();
        BooksDto.addInitialData();
        /** Root API */
        environment.addResource(RootResource.class);
        /** Books APIs */
        environment.addResource(BookResource.class);
        /** UI Resources */
        environment.addResource(HomeResource.class);

    }
}
