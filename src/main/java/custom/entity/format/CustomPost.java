package custom.entity.format;

import interceptors.MyWriterInterceptor;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class CustomPost {

	public CustomPost() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder
				.newClient()
				.register(MyWriterInterceptor.class);
		
		Response response = client.target("http://localhost:8080/RestServer/res/custom").request().post(Entity.entity("Johny/Smithy", "custom/format"));
		
		System.out.println("Request response: "+response+",\nEntity from response: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new CustomPost();
	}
}