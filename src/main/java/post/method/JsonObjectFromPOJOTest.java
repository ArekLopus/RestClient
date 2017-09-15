package post.method;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import utils.User;

//http://localhost:8080/TestWeb/res/posts/jsonObject
//jersey-media-moxy / jersey-media-json-jackson needed for automatic conversion POJO -> JSON
public class JsonObjectFromPOJOTest {

	public JsonObjectFromPOJOTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/posts/jsonObject");
		Response response = wt
			.request()
			.post(Entity.json(new User("Johny","Bravo")));
		
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new JsonObjectFromPOJOTest();
	}
	
}
