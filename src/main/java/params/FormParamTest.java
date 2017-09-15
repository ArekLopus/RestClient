package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

//http://localhost:8080/TestWeb/res/params/form
public class FormParamTest {

	public FormParamTest() {
		
		Client client = ClientBuilder.newClient();
		
		Form form = new Form()
    		.param("name", "John")
    		.param("surname", "Rambo");
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/params/form");
		Response response = wt
			.request()
			.post(Entity.form(form));
		
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new FormParamTest();
	}
	
}
