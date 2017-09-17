package response;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ResponseTest {

	public ResponseTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/test/response");
		Response response = wt
			.request()
			.get();
		
		System.out.println("Response: "+response);
		System.out.println("------------------------------------------");
		System.out.println("Headers: : "+response.getHeaders());
		System.out.println("------------------------------------------");
		System.out.println("response.getMetadata(): "+response.getMetadata());
		System.out.println("------------------------------------------");
		System.out.println("response.getStringHeaders(): "+response.getStringHeaders());
		System.out.println("------------------------------------------");
		System.out.println("response.getLength(): "+response.getLength());
		System.out.println("response.getStatus(): "+response.getStatus());
		System.out.println("response.bufferEntity(): "+response.bufferEntity());
		System.out.println("response.getAllowedMethods(): "+response.getAllowedMethods());
		System.out.println("response.getCookies(): "+response.getCookies());
		System.out.println("response.getDate(): "+response.getDate());
		System.out.println("response.getEntity(): "+response.getEntity());
		System.out.println("response.getLanguage(): "+response.getLanguage());
		System.out.println("response.getLastModified(): "+response.getLastModified());
		System.out.println("response.getLinks(): "+response.getLinks());
		System.out.println("response.getLocation(): "+response.getLocation());
		System.out.println("response.getMediaType(): "+response.getMediaType());
		System.out.println("response.getStatusInfo(): "+response.getStatusInfo());
		System.out.println("response.hasEntity(): "+response.hasEntity());
		System.out.println("response.readEntity(String.class): "+response.readEntity(String.class));
		
		
		client.close();
	}
	
	public static void main(String[] args) {
		new ResponseTest();
	}
	
}
