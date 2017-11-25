package basics;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class Basics_Client {

	public Basics_Client() throws InterruptedException, ExecutionException {
		
		//-Clients are heavy-weight objects that manage the client-side communication infrastructure.
		// Initialization as well as disposal of a Client instance may be a rather expensive operation.
		// It is therefore advised to construct only a small number of Client instances in the app.
		//-Client instances must be properly closed before being disposed to avoid leaking resources.
		Client client = ClientBuilder.newClient();
		
		String string = client.target("http://localhost:8080/RestServer/res/test").request().get(String.class);
		Response response = client.target("http://localhost:8080/RestServer/res/test").request().get();
		
		
		System.out.println("Entity response: "+string);
		System.out.println("Request response: "+response+",\nEntity from response: "+response.readEntity(String.class));
		
		System.out.println("Main thread finished");
		
		
		//-Client instances must be properly closed before being disposed to avoid leaking resources.
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new Basics_Client();
	}
}