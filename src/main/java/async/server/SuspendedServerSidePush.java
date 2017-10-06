package async.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

//-If we post something on the server side client will read it
public class SuspendedServerSidePush {

	public SuspendedServerSidePush() {
		
		Client client = ClientBuilder.newClient();
		
		System.out.println("Client started.");
		
		final WebTarget wt = client.target("http://localhost:8080/TestWeb/res/threads/push");
		wt.request().async().get(new InvocationCallback<String>() {

				@Override
				public void completed(String value) {
					System.out.println("Value: " + value);
				    wt.request().async().get(this);
				}

				@Override
				public void failed(Throwable throwable) {
					System.out.println("Failed! "+throwable.getMessage());
					
				}
				
			});
		
		//client.close();
	}
	
	public static void main(String[] args) {
		new SuspendedServerSidePush();
	}
	
}