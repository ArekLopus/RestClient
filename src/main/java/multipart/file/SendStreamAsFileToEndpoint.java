package multipart.file;

import java.io.InputStream;
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
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

//https://jersey.github.io/apidocs/latest/jersey/org/glassfish/jersey/media/multipart/file/StreamDataBodyPart.html
//StreamDataBodyPart - Represents an InputStream based file submission as a part of the multipart/form-data.
// It sets the InputStream as a body part with the default MediaType.APPLICATION_OCTET_STREAM_TYPE (if not specified by the user).
// Note: The MIME type of the entity cannot be automatically predicted as in case of FileDataBodyPart.
// The filename of the attachment is set by the user or defaults to the part's name.
public class SendStreamAsFileToEndpoint {

	public SendStreamAsFileToEndpoint() throws InterruptedException, ExecutionException {
		
		Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        
        WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/file/upload");
        
        String fileName = "test.txt";
        InputStream is = getClass().getResourceAsStream("/"+fileName);
        
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        
        StreamDataBodyPart sdbp = new StreamDataBodyPart("file", is, fileName, MediaType.APPLICATION_OCTET_STREAM_TYPE);
        
        formDataMultiPart.bodyPart(sdbp);
        
        Builder invocationBuilder = webTarget.request();
        Response response = invocationBuilder.post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));
		
        System.out.println("Response: "+response);
		System.out.println("Entity: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new SendStreamAsFileToEndpoint();
	}
}