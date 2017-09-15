package post.method;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//https://jersey.github.io/documentation/latest/media.html#d0e8603	- XML support
//http://localhost:8080/TestWeb/res/posts/xmlSAX
public class XMLConsumerSAXSourceTest {

	public XMLConsumerSAXSourceTest() {
		String xmlStr = "<user><name>John</name><surname>Smith</surname></user>";
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/posts/xmlSAX");
		Response response = wt
			.request()
			.post(Entity.xml(xmlStr));
		
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new XMLConsumerSAXSourceTest();
	}
	
}
