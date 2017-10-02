package serverExceptionMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
public class GETMethod {

	public GETMethod() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/test/exception");
		Response response = wt
			.request()
			.get();
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new GETMethod();
	}
	
}
