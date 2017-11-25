package test;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class TestConnection {

	public TestConnection() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		long start = System.currentTimeMillis();
		
		Response response = client.target("http://localhost:8080/RestServer/res/test").request().get();
		//Response response = client.target("https://jsonplaceholder.typicode.com/posts/1").request().get();
		//Response response = client.target("http://services.groupkt.com/country/get/iso2code/PL").request().get();
		
		long end = System.currentTimeMillis() - start;
		System.out.println("Time: "+end+" ms");
		
		System.out.println("Request response: "+response+",\nEntity from response: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new TestConnection();
	}
}