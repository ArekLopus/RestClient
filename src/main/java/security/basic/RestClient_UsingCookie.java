package security.basic;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class RestClient_UsingCookie {

	public RestClient_UsingCookie() {
		
		//Jersey Core Client needed
		HttpAuthenticationFeature basicAuth = HttpAuthenticationFeature.basic("arek", "arek");
		
		Client client = ClientBuilder.newClient().register(basicAuth);
		
		//First we log in to make a session
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/insecured/login");
		
		Response res = wt
			.request(MediaType.TEXT_HTML)
			.get();
		
		System.out.println("Logged?: "+res.readEntity(String.class));
		
		Map<String, NewCookie> cookies = res.getCookies();
		System.out.println("Cookies from response -> "+cookies.keySet());
		System.out.println("JSESSIONID -> "+cookies.get("JSESSIONID").getValue());
		
		//Now we are getting secured resource setting a session cookie 
		WebTarget wt2 = client.target("http://localhost:8080/TestWeb/res/secured");
		String st = wt2
			.request(MediaType.TEXT_HTML)
			.cookie(cookies.get("JSESSIONID"))
			.get(String.class);
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new RestClient_UsingCookie();
	}
	
}
