package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;

//-When we use InvocationCallbact with no Response type we cant get Response object.
//-WebApplicationException (or subclass) has getResponse().
@SuppressWarnings("unused")
public class AsyncEntityWithCallbackException {

	public AsyncEntityWithCallbackException() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		//WebTarget target = client.target("http://localhost:8080/TestWeb/res/threads/async");
		WebTarget target = client.target("http://localhost:8080/TestWeb/res/threads/async1");	//error
		
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
		    	if (throwable instanceof WebApplicationException) {
		    		WebApplicationException wae = (WebApplicationException)throwable;
		            System.err.println("Failed with status: " + wae.getResponse());
		        } else if (throwable instanceof ResponseProcessingException) {
		        	ResponseProcessingException rpe = (ResponseProcessingException)throwable;
		            System.err.println("Failed with status: " + rpe.getResponse());
		        } else {
		            throwable.printStackTrace();
		        }
		    	client.close();
		    }
		});
		System.out.println("----- Main thread terminated -----");
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new AsyncEntityWithCallbackException();
	}
}