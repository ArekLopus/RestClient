package basics;

import java.io.File;
import java.net.URI;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import utils.User;

public class ClientConfiguring {

	public ClientConfiguring() throws InterruptedException, ExecutionException {
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(MultiPartFeature.class);
		Client client =  ClientBuilder.newClient(clientConfig);
		
		
		Client client2 = ClientBuilder.newClient();
		client2.register(MultiPartFeature.class);
		
		
		WebTarget webTarget = client.target("http://localhost:8080/RestServer/res/multipart/FormDataMultiPart");
		WebTarget webTarget2 = client2.target("http://localhost:8080/RestServer/res/multipart/FormDataMultiPart");

        FormDataMultiPart multipart = new FormDataMultiPart();
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", new File(URI.create(getClass().getResource("/test.txt").toExternalForm())));
        FormDataBodyPart formDataBodyPart = new FormDataBodyPart("user", new User("John","Wayne"), MediaType.APPLICATION_JSON_TYPE);
        multipart.field("enabled", "false");
        multipart.bodyPart(formDataBodyPart);
        multipart.bodyPart(fileDataBodyPart);
        
        Response response = webTarget.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA_TYPE));
        Response response2 = webTarget2.request().post(Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA_TYPE));;
        
        System.out.println("Request response: "+response+",\nEntity from response: "+response.readEntity(String.class));
        System.out.println("------------------------------------");
        System.out.println("Request response: "+response2+",\nEntity from response: "+response2.readEntity(String.class));
        
		System.out.println("----- Main thread finished -----");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new ClientConfiguring();
	}
}