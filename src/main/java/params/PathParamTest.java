package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/TestWeb/res/params/John-Smith
public class PathParamTest {

	public PathParamTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/params/{name}-{surname}");
		String st = wt
			.resolveTemplate("name", "John")
			.resolveTemplate("surname", "Smith")
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new PathParamTest();
	}
	
}
