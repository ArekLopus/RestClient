package methods;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//The OPTIONS method is used to describe the communication options for the target resource.
public class OPTIONSMethod {

	public OPTIONSMethod() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/test");
		Response response = wt
			.request()
			.options();
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new OPTIONSMethod();
	}
	
}
