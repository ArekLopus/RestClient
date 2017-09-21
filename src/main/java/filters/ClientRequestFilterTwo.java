package filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

@Priority(1)
public class ClientRequestFilterTwo implements ClientRequestFilter {
	
	@Override
    public void filter(ClientRequestContext requestContext) throws IOException {
		System.out.println("Client request filter 2, priority 1");
		System.out.println("-------------------------------");
    }

}
