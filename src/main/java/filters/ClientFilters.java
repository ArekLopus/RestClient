package filters;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ClientFilters {

	public ClientFilters() throws UnsupportedEncodingException {
		
		Client client = ClientBuilder.newClient();
		client.register(ClientRequestFilterOne.class, 3);	//Priority here overrides @Priority in a listener class
		client.register(ClientRequestFilterTwo.class, 2);
		client.register(ClientResponseFilterTest.class);
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/test");
		
		String st = wt.request().cookie("test",	"abc").get(String.class);
		
		System.out.println("Response: "+st);
		
		client.close();
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		new ClientFilters();
	}
	
	

}
