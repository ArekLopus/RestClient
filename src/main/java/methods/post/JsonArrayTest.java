package methods.post;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//http://localhost:8080/TestWeb/res/posts/jsonArray
//curl -i -X POST -H "Content-Type: application/json" -d "[\"Johny\",\"Bravo\"]" http://localhost:8080/TestWeb/res/posts/jsonArray
public class JsonArrayTest {

	public JsonArrayTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/posts/jsonArray");
		Response response = wt
			.request()
			.post(Entity.json("[\"Johny\",\"Bravo\"]"));
		
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new JsonArrayTest();
	}
	
}
