package filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class SecurityRequestFilter {

	public SecurityRequestFilter() throws UnsupportedEncodingException {
		
		Client client = ClientBuilder.newClient();
		client.register(new AuthenticatorFilter("user", "pass"));
		client.register(new ResponseFilter());
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/secured/secured2");
		
		String st = wt
			.request(MediaType.TEXT_HTML)
			.cookie("cookie", "just for fun")
			.get(String.class);
		
		System.out.println("Response: "+st);
		
		client.close();
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		new SecurityRequestFilter();
	}
	
	
	private class AuthenticatorFilter implements ClientRequestFilter {

		private String user;
	    private String pass;

	    public AuthenticatorFilter(String user, String pass) {
	        this.user = user;
	        this.pass = pass;
	    }
	    
	    public void filter(ClientRequestContext requestContext) throws IOException {
	    	MultivaluedMap<String, Object> headers = requestContext.getHeaders();
	        headers.add(HttpHeaders.AUTHORIZATION, getBase64EncodedCredentials());
	        System.out.println("Request headers: "+requestContext.getHeaders());
	    }

	    private String getBase64EncodedCredentials() {
	        String credentials = this.user + ":" + this.pass;
	        try {
	        	return "Basic " + new String(Base64.getEncoder().encode(credentials.getBytes("UTF-8")));
	        } catch (UnsupportedEncodingException ex) {
	           throw new IllegalStateException("Cannot encode with UTF-8", ex);
	        }
	    }
	}
	
	private class ResponseFilter implements ClientResponseFilter {

		@Override
		public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
			MultivaluedMap<String,String> headers = responseContext.getHeaders();
			System.out.println("Response headers: "+headers);
			System.out.println("Cookies: "+responseContext.getCookies());
			System.out.println("Status: "+responseContext.getStatus());
			System.out.println("Status Info: "+responseContext.getStatusInfo());
		}
		
	}
	
}
