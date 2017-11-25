package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//-If this annotation is not used and the corresponding metadata is not present in the request, the value will be an empty collection for List, Set or SortedSet,
// null for other object types, and the Java-defined default for primitive types.
//http://localhost:8080/TestWeb/res/params/default  //?name=John&surname=Smith
public class DefaultValueWithQueryParamTest {

	public DefaultValueWithQueryParamTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/params/default");
		String st = wt
			//.queryParam("name", "John")
			//.queryParam("surname", "Smith")
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new DefaultValueWithQueryParamTest();
	}
	
}
