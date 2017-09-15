package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


public class AsyncResponseWithCallback {

	@SuppressWarnings("unused")
	public AsyncResponseWithCallback() throws InterruptedException, ExecutionException {
		
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TestWeb/res/thread/async");
		
		Future<Response> responseFuture = target.request().async().get(new InvocationCallback<Response>() {
		    @Override
		    public void completed(Response response) {
		    	System.out.println("Response received, status code: " + response.getStatus()+", thread: "+Thread.currentThread().getName());
		    	System.out.println("Responses entity: "+response.readEntity(String.class));
		    	client.close();
		    }
		 
		    @Override
		    public void failed(Throwable throwable) {
		    	System.out.println("Invocation failed.");
		    	throwable.printStackTrace();
		    	client.close();
		    }
		});
		System.out.println("Main thread terminated");
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new AsyncResponseWithCallback();
	}
}