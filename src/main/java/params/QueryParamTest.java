package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/TestWeb/res/params/query?name=John&surname=Smith
public class QueryParamTest {

	public QueryParamTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/params/query");
		String st = wt
			.queryParam("name", "John")
			.queryParam("surname", "Smith")
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new QueryParamTest();
	}
	
}
