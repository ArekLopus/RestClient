package response;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ResponseEntityTypes {

	public ResponseEntityTypes() {
		
		Client client = ClientBuilder.newClient();
		
		
		Response response = client.target("http://localhost:8080/RestServer/res/test/response").request().get();
		
		System.out.println("response.getEntity(): "+response.getEntity());
		
		System.out.println("response.bufferEntity(): "+response.bufferEntity());
		
		System.out.println("response.getEntity(): "+response.getEntity());
		
		Response response2 = client.target("http://localhost:8080/RestServer/res/streamingOutput/string").request().get();
		System.out.println("response.getEntity(): "+response2.getEntity());
		
		System.out.println("response.getEntity(): "+response.getEntity());
		client.close();
	}
	
	public static void main(String[] args) {
		new ResponseEntityTypes();
	}
	
}
