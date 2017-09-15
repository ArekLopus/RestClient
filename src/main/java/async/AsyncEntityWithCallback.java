package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;


public class AsyncEntityWithCallback {

	@SuppressWarnings("unused")
	public AsyncEntityWithCallback() throws InterruptedException, ExecutionException {
		
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TestWeb/res/thread/async");
		
		Future<String> responseFuture = target.request().async().get(new InvocationCallback<String>() {
		    @Override
		    public void completed(String response) {
		    	System.out.println("Clients thread: "+Thread.currentThread().getName());
		    	System.out.println("Response: " + response);
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
		new AsyncEntityWithCallback();
	}
}