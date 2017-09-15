package multipart.file;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

//https://jersey.github.io/apidocs/latest/jersey/org/glassfish/jersey/media/multipart/file/FileDataBodyPart.html
//FileDataBodyPart - An extension of FormDataBodyPart for associating File File as a body part entity.
// This class may be used to create body parts that contains a file attachments.
// Appropriate Content-Disposition parameters and Content-Type header will be derived from the file.
public class SendFileToEndpoint {

	public SendFileToEndpoint() throws InterruptedException, ExecutionException {
		
//		ClientConfig clientConfig = new ClientConfig();
//        clientConfig.register(MultiPartFeature.class);
//        Client client =  ClientBuilder.newClient(clientConfig);
        Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        
        WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/file/upload");

        //Set file
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart ("file", new File("d:/download/alice30.txt"));
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart.bodyPart(fileDataBodyPart);

        Builder invocationBuilder = webTarget.request();
        
        Response response = invocationBuilder.post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));
		
        System.out.println("Response: "+response);
		System.out.println("Entity: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new SendFileToEndpoint();
	}
}