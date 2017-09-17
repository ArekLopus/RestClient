package methods;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//The DELETE method deletes the specified resource.
public class DELETEMethod {

	public DELETEMethod() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/test");
		Response response = wt
			.request()
			.delete();
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new DELETEMethod();
	}
	
}
