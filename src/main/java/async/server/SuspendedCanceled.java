package async.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class SuspendedCanceled {

	public SuspendedCanceled() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/threads/suspendedCancel");
		Response response = wt
			.request()
			.get();
		
		System.out.println(response);
		System.out.println("--------------------------");
		System.out.println("Retry-After: "+response.getHeaderString("Retry-After"));
		System.out.println("--------------------------");
		System.out.println(response.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new SuspendedCanceled();
	}
	
}