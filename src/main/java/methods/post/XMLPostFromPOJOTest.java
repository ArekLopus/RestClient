package methods.post;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import utils.User;

//<artifactId>jersey-media-jaxb</artifactId> needed for POJO -> XML
//https://jersey.github.io/documentation/latest/media.html#d0e8603	- XML support
//http://localhost:8080/TestWeb/res/posts/xmlConsumer
public class XMLPostFromPOJOTest {

	public XMLPostFromPOJOTest() {

		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/posts/xmlConsumer");
		Response response = wt
			.request()
			.post(Entity.xml(new User("John","Xmlsky")));
		
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new XMLPostFromPOJOTest();
	}
	
}
