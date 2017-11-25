package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/TestWeb/res/params/cookie
public class CookieParamTest {

	public CookieParamTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/params/cookie");
		String st = wt
			.request(MediaType.TEXT_HTML)
			.cookie("cookieName", "This is a cookie passed from a client")
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new CookieParamTest();
	}
	
}
