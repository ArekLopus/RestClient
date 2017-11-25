package streamingoutput;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ReadFileFromEndpoint {

	public ReadFileFromEndpoint() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		String string = client.target("http://localhost:8080/RestServer/res/streamingOutput/file").request().get(String.class);
		System.out.println("Downloaded file size: "+string.length());
		//System.out.println("Downloaded file: "+string);
		
		Response res = client.target("http://localhost:8080/RestServer/res/streamingOutput/string").request().get();
		System.out.println("Response: "+res);
		System.out.println("Entity: "+res.readEntity(String.class));
		
		System.out.println("----- Main thread finished -----");
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new ReadFileFromEndpoint();
	}
}