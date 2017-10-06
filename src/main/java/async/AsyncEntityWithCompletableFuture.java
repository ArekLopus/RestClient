package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class AsyncEntityWithCompletableFuture {

	public AsyncEntityWithCompletableFuture() throws InterruptedException, ExecutionException {
		
		
		Client client = ClientBuilder.newClient();
		
		AsyncInvoker asyncInv = client.target("http://localhost:8080/TestWeb/res/threads/async")
			.request(MediaType.TEXT_HTML)
			.async();
		
//		CompletableFuture.runAsync( () -> {
//			try {
//				System.out.println("Thread of CF: "+Thread.currentThread().getName());
//				String entity = asyncInv.get().get().readEntity(String.class);
//				System.out.println(entity);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});		
		
		CompletableFuture<Void> cf = CompletableFuture.supplyAsync( () -> {
			try {
				String entity = asyncInv.get().get().readEntity(String.class);
				System.out.println("Thread of CFs supply: "+Thread.currentThread().getName());
				return entity;
			} catch (Exception e) {
				e.printStackTrace();
				return "Failed";
			}
		}).thenAcceptAsync( s -> {
			System.out.println("Thread of CFs accept: "+Thread.currentThread().getName());
			System.out.println("Response: "+s);
		});	
		
		System.out.println("----- Main thread terminated -----");
		cf.get();		//otherwise no result, clients thread terminates
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new AsyncEntityWithCompletableFuture();
	}
}