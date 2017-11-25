package async.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//-JAX-RS implementations are REQUIRED to generate a ServiceUnavailableException, a subclass of WebApplicationException
// with its status set to 503, if the timeout value is reached and no timeout handler is registered.
public class ServersMethodTimeout {

	public ServersMethodTimeout() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/threads/timeout");
		Response response = wt
			.request()
			.get();
		
		System.out.println(response);
		System.out.println("--------------------------");
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new ServersMethodTimeout();
	}
	
}