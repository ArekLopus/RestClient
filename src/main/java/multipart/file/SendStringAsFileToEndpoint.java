package multipart.file;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

//https://jersey.github.io/apidocs/latest/jersey/org/glassfish/jersey/media/multipart/FormDataBodyPart.html
//FormDataBodyPart - Subclass of BodyPart with specialized support for media type multipart/form-data. See RFC 2388 for the formal definition of this media type.
public class SendStringAsFileToEndpoint {

	public SendStringAsFileToEndpoint() throws InterruptedException, ExecutionException {
		
        Client client =  ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        
        WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/file/upload");

        String value = "Hello World!";
        
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        
        FormDataContentDisposition dis = FormDataContentDisposition
                .name("file")
                .fileName("test.txt")
                .size(value.getBytes().length)
                .build();
        
        FormDataBodyPart bodyPart = new FormDataBodyPart(dis, value);
        
        formDataMultiPart.bodyPart(bodyPart);
        
        Builder invocationBuilder = webTarget.request();
        Response response = invocationBuilder.post(Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA));
		
        System.out.println("Response: "+response);
		System.out.println("Entity: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new SendStringAsFileToEndpoint();
	}
}