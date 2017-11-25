package multipart;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import utils.User;

//jersey-media-moxy / jersey-media-json-jackson needed for automatic conversion POJO -> JSON!
//jersey-media-jaxb for POJO -> XML
@SuppressWarnings("unused")
public class ReadMultipartFromEndpoint {

	public ReadMultipartFromEndpoint() throws InterruptedException, ExecutionException, IOException {
		
        Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        
        WebTarget webTarget = client.target("http://localhost:8080/RestServer/res/multipart/MultiPart");

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
        
		MultiPart readEntity = response.readEntity(MultiPart.class);
        List<BodyPart> bodyParts = readEntity.getBodyParts();
        System.out.println("Body Partss: "+bodyParts);
        //System.out.println(bp.getEntityAs(String.class));
        
        for(BodyPart bp : bodyParts) {
        	switch (bp.getMediaType().toString()) {
				case "text/plain" : System.out.println(bp.getEntityAs(String.class)); break;
				case "application/xml" : System.out.println(bp.getEntityAs(User.class)); break;
				case "application/json" : System.out.println(bp.getEntityAs(User.class)); break;
				default: break;
			}
        }
        
	}
	
	public static void main(String[] args) throws Exception {
		new ReadMultipartFromEndpoint();
	}
}
