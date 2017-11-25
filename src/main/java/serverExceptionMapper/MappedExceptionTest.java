package serverExceptionMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class MappedExceptionTest {

	public MappedExceptionTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/exception/mapped");
		Response response = wt
			.request()
			.get();
		
		System.out.println("Response: "+response);
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("Entity: "+response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new MappedExceptionTest();
	}
	
}
