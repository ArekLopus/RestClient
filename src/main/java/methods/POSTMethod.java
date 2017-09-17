package methods;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//The POST method is used to submit an entity to the specified resource, often causing a change in state or side effects on the server
public class POSTMethod {

	public POSTMethod() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/test");
		Response response = wt
			.request()
			.post(Entity.html("<h3>Hello from POST mathod</h3>"));
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new POSTMethod();
	}
	
}
