package validation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ValidateEntityBodyCustomAnn {

	public ValidateEntityBodyCustomAnn() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/validation/user2");
		Response response = wt
			.request()
			//.post(Entity.json("{\"name\":\"John\",\"surname\":\"Smith\"}"));
			.post(Entity.json("{\"surname\":\"Smith\"}"));
		
		System.out.println(response);
		System.out.println("--------------------------");
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new ValidateEntityBodyCustomAnn();
	}
	
}