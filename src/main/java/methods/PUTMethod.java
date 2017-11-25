package methods;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//The PUT method replaces all current representations of the target resource with the request payload.
public class PUTMethod {

	public PUTMethod() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/test");
		Response response = wt
			.request()
			.put(Entity.html("<h3>Hello from PUT mathod</h3>"));
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new PUTMethod();
	}
	
}
