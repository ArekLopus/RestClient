package invocation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AsyncInvokerTest2 {

	public AsyncInvokerTest2() throws InterruptedException, ExecutionException {
		
		
		Client client = ClientBuilder.newClient();
		
		Invocation inv = client.target("http://localhost:8080/TestWeb/res/thread/sync")
			.request(MediaType.TEXT_HTML)
			.buildGet();
		String abc = inv.invoke().readEntity(String.class);
		System.out.println(abc);
		
		AsyncInvoker asyncInv = client.target("http://localhost:8080/TestWeb/res/thread/async")
			.request(MediaType.TEXT_HTML)
			.async();
		
		//Future<Response> future = asyncInv.get();
		//String abc2 = future.get().readEntity(String.class);
		//System.out.println(abc2);
		
		CompletableFuture.supplyAsync( () -> {
			try {
				System.out.println("Thread: "+Thread.currentThread().getName());
				return asyncInv.get().get().readEntity(String.class);
			} catch (Exception e) {
				e.printStackTrace();
				return "Blah";
			}
		} ).thenAccept( System.out::println);		
		
		//System.out.println("Passed");
		//Future<String> future2 = inv.submit(String.class);
		//String abc3 = future2.get();
		//System.out.println(abc3);
		
//		final Future<Response> responseFuture = target().path("http://example.com/resource/")
//		        .request().async().get(new InvocationCallback<Response>() {
//		            @Override
//		            public void completed(Response response) {
//		                System.out.println("Response status code "
//		                        + response.getStatus() + " received.");
//		            }
//		 
//		            @Override
//		            public void failed(Throwable throwable) {
//		                System.out.println("Invocation failed.");
//		                throwable.printStackTrace();
//		            }
//		        });
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new AsyncInvokerTest2();
	}
}