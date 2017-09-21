package filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class ClientResponseFilterTest implements ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		
		System.out.println("Client response filter");
		
		System.out.println("getLength(): "+responseContext.getLength());
		System.out.println("getStatus(): "+responseContext.getStatus());
		System.out.println("hasEntity(): "+responseContext.hasEntity());
		System.out.println("getAllowedMethods(): "+responseContext.getAllowedMethods());
		System.out.println("getCookies(): "+responseContext.getCookies());
		System.out.println("getDate(): "+responseContext.getDate());
		System.out.println("getHeaders(): "+responseContext.getHeaders());
		System.out.println("getLanguage(): "+responseContext.getLanguage());
		System.out.println("getMediaType(): "+responseContext.getMediaType());
		System.out.println("getStatusInfo(): "+responseContext.getStatusInfo());
		
		System.out.println("-------------------------------");
	}
	

}
