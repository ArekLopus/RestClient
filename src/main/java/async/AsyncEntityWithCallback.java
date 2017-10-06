package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;

//-By default, invocations are synchronous but can be set to run asynchronously by calling the async()
// and (optionally) registering an instance of InvocationCallback:
//-Note that the call to get() after calling async() returns immediately without blocking the caller's thread.
// The response type is specified as a type parameter to InvocationCallback.
// The completed() is called when the invocation completes successfully and a response is available;
// the failed() is called with an instance of Throwable when the invocation fails.
//-All asynchronous invocations return an instance of Future<T> here the type parameter T matches the type specified in InvocationCallback.
// This instance can be used to monitor or cancel the asynchronous invocation.
@SuppressWarnings("unused")
public class AsyncEntityWithCallback {

	public AsyncEntityWithCallback() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TestWeb/res/threads/async");
		//WebTarget target = client.target("http://localhost:8080/TestWeb/res/threads/async1");	//error
		
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
		new AsyncEntityWithCallback();
	}
}