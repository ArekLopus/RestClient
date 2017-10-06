package validation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//Randomly generates empty name
public class ValidateReturnEntityBody {

	public ValidateReturnEntityBody() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/validation/user");
		Response response = wt
			.request()
			.get();
		
		System.out.println(response);
		System.out.println("--------------------------");
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new ValidateReturnEntityBody();
	}
	
}
