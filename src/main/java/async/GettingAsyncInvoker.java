package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//thread/sync and async are sync/async on the server side!
public class GettingAsyncInvoker {

	@SuppressWarnings("unused")
	public GettingAsyncInvoker() throws InterruptedException, ExecutionException {
		
		
		Client client = ClientBuilder.newClient();
		
		Invocation inv = client.target("http://localhost:8080/TestWeb/res/threads/sync")
			.request(MediaType.TEXT_HTML)
			.buildGet();
		
		//invoke() - Synchronously invoke the request and receive a response back.
		String syncClient1 = inv.invoke(String.class);					//returns entity
		String syncClient2 = inv.invoke(). readEntity(String.class);	//returns Response (we get more info (cookies, headers, status, etc))
		
		System.out.println(syncClient2);
		
		
		//submit() - Submits the request for an asynchronous invocation and receive a future response of the specified type back.
		Future<String> future1 = inv.submit(String.class);		//returns Future<Entity>
		Future<Response> future2 = inv.submit();				//returns Future<Response>
		
		System.out.println("----- Main thread terminated -----");
		
		inv.submit(new InvocationCallback<Response>() {
			@Override
			public void completed(Response response) {
				System.out.println("Async response from submit(): "+response.readEntity(String.class)+", thread: "+Thread.currentThread().getName());
			}

			@Override
			public void failed(Throwable throwable) {
				System.out.println("Async failed");
			}
			
		});
		
		AsyncInvoker asyncInv = client.target("http://localhost:8080/TestWeb/res/threads/sync")
			.request(MediaType.TEXT_HTML)
			.async();
		
		Future<Response> future = asyncInv.get();
		String abc2 = future.get().readEntity(String.class);
		System.out.println(abc2);
		
		
		asyncInv.get(new InvocationCallback<Response>() {
			@Override
			public void completed(Response response) {
				System.out.println("Async response from AsyncInvoker: "+response.readEntity(String.class)+", thread: "+Thread.currentThread().getName());
			}

			@Override
			public void failed(Throwable throwable) {
				System.out.println("Async failed");
			}
			
		});
		
		Thread.sleep(2000);
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new GettingAsyncInvoker();
	}
}