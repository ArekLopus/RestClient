package multipart;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import utils.User;

//jersey-media-moxy / jersey-media-json-jackson needed for automatic conversion POJO -> JSON!
//jersey-media-jaxb for POJO -> XML
@SuppressWarnings("unused")
public class ReadFormDataMultipartFromEndpoint {

	public ReadFormDataMultipartFromEndpoint() throws InterruptedException, ExecutionException, IOException {
		
        Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        
        WebTarget webTarget = client.target("http://localhost:8080/RestServer/res/multipart/FormDataMultiPart");

        Response response = webTarget.request().get();
        
        System.out.println("Response: "+response);
        
        //printResponseAsString(response);
        printResponseElements(response);
        
		System.out.println("--- Main thread finished ---");
		
		client.close();
	}
	
	private void printResponseAsString(Response response) {
		String str = response.readEntity(String.class);
		System.out.println("Responses entity:\n"+str);
	}
	
	
	private void printResponseElements(Response response) {
        
		FormDataMultiPart readEntity = response.readEntity(FormDataMultiPart.class);
        
        System.out.println("Fields: "+readEntity.getFields());
        
        String enabled = readEntity.getField("enabled").getValue();
        System.out.println("Enbd -> "+enabled);
        User user = readEntity.getField("user").getEntityAs(User.class);
        System.out.println("User -> "+user);
        String file = readEntity.getField("file").getEntityAs(String.class);
        System.out.println("File -> "+file);
	}
	
	public static void main(String[] args) throws Exception {
		new ReadFormDataMultipartFromEndpoint();
	}
}
