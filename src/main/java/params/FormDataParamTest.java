package params;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import utils.User;

//jersey-media-moxy / jersey-media-json-jackson needed for automatic conversion POJO -> JSON
//jersey-media-jaxb for POJO -> XML
//Multipart!
//formData(@FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition info,
//	@DefaultValue("true") @FormDataParam("enabled") boolean enabled, @FormDataParam("user") User u)
public class FormDataParamTest {

	public FormDataParamTest() throws InterruptedException, ExecutionException, IOException {
		
        Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        
        WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/multipart/FormDataMultiPart");

        FormDataMultiPart multipart = new FormDataMultiPart();
        
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", new File("d:/download/alice30.txt"));
        FormDataBodyPart formDataBodyPart = new FormDataBodyPart("user", new User("John","Wayne"), MediaType.APPLICATION_JSON_TYPE);
        		
        multipart.field("enabled", "false");
        multipart.bodyPart(fileDataBodyPart);
        multipart.bodyPart(formDataBodyPart);
        
        Response response = webTarget.request().post(Entity.entity(multipart, multipart.getMediaType()));
        
        System.out.println("Response: "+response);
		System.out.println("Entity: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		multipart.cleanup();
		multipart.close();
		client.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new FormDataParamTest();
	}
}
