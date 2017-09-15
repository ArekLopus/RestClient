package post.method;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//http://localhost:8080/TestWeb/res/posts/jsonObject
//curl -i -X POST -H "Content-Type: application/json" -d "{\"name\": \"Johny\", \"surname\": \"Bravo\" }" http://localhost:8080/TestWeb/res/posts/jsonObject
public class JsonObjectTest {

	public JsonObjectTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/posts/jsonObject");
		Response response = wt
			.request()
			.post(Entity.json("{\"name\":\"Johny\",\"surname\":\"Bravo\"}"));
		
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new JsonObjectTest();
	}
	
}
