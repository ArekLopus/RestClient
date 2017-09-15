package multipart;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import utils.User;

//jersey-media-moxy / jersey-media-json-jackson needed for automatic conversion POJO -> JSON
//jersey-media-jaxb for POJO -> XML
public class SendFormDataMultiPartToEndpoint {

	public SendFormDataMultiPartToEndpoint() throws InterruptedException, ExecutionException, IOException {
		
        //Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(MultiPartFeature.class);
		
		Client client =  ClientBuilder.newClient(clientConfig);
        
        WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/multipart/FormDataMultiPart");

        FormDataMultiPart multipart = new FormDataMultiPart();
        
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", new File("d:/download/alice30.txt"));
        FormDataBodyPart formDataBodyPart = new FormDataBodyPart("user", new User("John","Wayne"), MediaType.APPLICATION_JSON_TYPE);
        		
        multipart.field("enabled", "false");
        multipart.bodyPart(fileDataBodyPart);
        multipart.bodyPart(formDataBodyPart);
        //multipart.field("user", "{\"name\":\"Johny\",\"surname\":\"Bravo\"}", MediaType.APPLICATION_JSON_TYPE);
        //multipart.field("user", new User("John","Wayne"), MediaType.APPLICATION_JSON_TYPE);
        
        Builder invocationBuilder = webTarget.request();
        
        Response response = invocationBuilder.post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA_TYPE));
        
        System.out.println("Response: "+response);
		System.out.println("Entity: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		multipart.cleanup();
		multipart.close();
		client.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new SendFormDataMultiPartToEndpoint();
	}
}
