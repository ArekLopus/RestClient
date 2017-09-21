package filters;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

//Filters for this resource are registered in Servers DynamicFeatureTest.class
public class ServerFilters {

	public ServerFilters() throws UnsupportedEncodingException {
		
		Client client = ClientBuilder.newClient();
		client.register(ClientRequestFilterOne.class);
		client.register(ClientRequestFilterTwo.class);
		client.register(ClientResponseFilterTest.class);
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/dynamicFeature");
		
		String st = wt.request().cookie("test",	"abc").get(String.class);
		
		System.out.println("Response: "+st);
		
		client.close();
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		new ServerFilters();
	}
	
	

}
