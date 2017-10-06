package async.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ReturnsCompletebleFurure {

	public ReturnsCompletebleFurure() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/threads/completableFuture");
		Response response = wt
			.request()
			.get();
		
		System.out.println(response);
		System.out.println("--------------------------");
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new ReturnsCompletebleFurure();
	}
	
}