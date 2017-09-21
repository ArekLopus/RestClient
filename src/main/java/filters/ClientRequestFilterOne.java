package filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

@Priority(5)
public class ClientRequestFilterOne implements ClientRequestFilter {
	
	@Override
    public void filter(ClientRequestContext requestContext) throws IOException {
		System.out.println("Client request filter 1, priority 5");
        System.out.println("getMethod(): "+requestContext.getMethod());
        System.out.println("hasEntity(): "+requestContext.hasEntity());
        System.out.println("getAcceptableLanguages(): "+requestContext.getAcceptableLanguages());
        System.out.println("getAcceptableMediaTypes(): "+requestContext.getAcceptableMediaTypes());
        System.out.println("getCookies(): "+requestContext.getCookies());
        System.out.println("getDate(): "+requestContext.getDate());
        System.out.println("getEntityClass(): "+requestContext.getEntityClass());
        System.out.println("getEntityType(): "+requestContext.getEntityType());
        System.out.println("getHeaders(): "+requestContext.getHeaders());
        System.out.println("getUri(): "+requestContext.getUri());
        System.out.println("getEntityStream(): If used in a filter is not available in response");
        System.out.println("-------------------------------");
        
        
    	
    	
    	
    }

}
