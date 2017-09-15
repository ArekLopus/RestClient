package security.basic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

public class RestClient_UsingCookie2 {

	public RestClient_UsingCookie2() {
		
		//With this approach we dont need any other dependencies
		Client client = ClientBuilder.newClient().register(new Authenticator("arek", "arek"));
		
		//First we log in to make a session
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/insecured/login");
		
		Response res = wt
			.request(MediaType.TEXT_HTML)
			.get();
		
		System.out.println("Logged?: "+res.readEntity(String.class));
		
		Map<String, NewCookie> cookies = res.getCookies();
		System.out.println("Cookies from response"+cookies.keySet()+" - "+cookies.get("JSESSIONID").getValue());
		
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
		new RestClient_UsingCookie2();
	}
	
	
	private class Authenticator implements ClientRequestFilter {

	    private final String user;
	    private final String password;

	    public Authenticator(String user, String password) {
	        this.user = user;
	        this.password = password;
	    }

	    public void filter(ClientRequestContext requestContext) throws IOException {
	        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
	        final String basicAuthentication = getBasicAuthentication();
	        headers.add("Authorization", basicAuthentication);

	    }

	    private String getBasicAuthentication() {
	        String token = this.user + ":" + this.password;
	        try {
	            return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
	        } catch (UnsupportedEncodingException ex) {
	            throw new IllegalStateException("Cannot encode with UTF-8", ex);
	        }
	    }
	}

	
}
