package custom.entity.format;

import interceptors.MyReaderInterceptor;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class CustomGet {

	public CustomGet() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder
				.newClient()
				.register(MyReaderInterceptor.class);
		
		Response response = client.target("http://localhost:8080/RestServer/res/custom").request("custom/format").get();
		
		System.out.println("Request response: "+response+",\nEntity from response: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new CustomGet();
	}
}