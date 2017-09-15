package multipart;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import utils.User;

//jersey-media-moxy / jersey-media-json-jackson needed for automatic conversion POJO -> JSON!
//jersey-media-jaxb for POJO -> XML
public class SendMultipartToEndpoint {

	public SendMultipartToEndpoint() throws InterruptedException, ExecutionException, IOException {
		
        Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		
        WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/multipart/MultiPart");
        
        MultiPart multiPartEntity = new MultiPart();
		multiPartEntity.bodyPart(new BodyPart().entity("hello"));
        multiPartEntity.bodyPart(new BodyPart(new User("John","Jonson"), MediaType.APPLICATION_JSON_TYPE));
        multiPartEntity.bodyPart(new BodyPart(new User("John","Xmlsky"), MediaType.APPLICATION_XML_TYPE));
        
        Builder invocationBuilder = webTarget.request();
        
        Response response = invocationBuilder.post(Entity.entity(multiPartEntity, "multipart/mixed"));
        
        System.out.println("Response: "+response);
		System.out.println("Entity: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		multiPartEntity.cleanup();
		multiPartEntity.close();
		client.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new SendMultipartToEndpoint();
	}
}
