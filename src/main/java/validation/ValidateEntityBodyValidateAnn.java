package validation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ValidateEntityBodyValidateAnn {

	public ValidateEntityBodyValidateAnn() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/validation/user");
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
		new ValidateEntityBodyValidateAnn();
	}
	
}