package methods;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//The PATCH method is used to apply partial modifications to a resource.
public class PATCHMethod {

	public PATCHMethod() {	//DOES NOT WORK
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/test");
		Response response = wt
			.request()
			.method("PATCH", Entity.html("<h3>Hello from POST mathod</h3>"));
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new PATCHMethod();
	}
	
}
