package ver21.reactive;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

//Both targets need 2 seconds to finish 
public class ReactiveTestCombine {

	public ReactiveTestCombine() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		long start = System.currentTimeMillis();
		
		CompletionStage<Response> completionStage = client.target("http://localhost:8080/RestServer/res/reactive/react1").request().rx().get();
		CompletionStage<Response> completionStage2 = client.target("http://localhost:8080/RestServer/res/reactive/react2").request().rx().get();
		
		CompletionStage<String> combined = completionStage.thenCombine(completionStage2, (r1, r2) -> {
			String readEntity = r1.readEntity(String.class);
			String readEntity2 = r2.readEntity(String.class);
			return readEntity + "\n" + readEntity2; 
		});
		
		combined.thenAccept( s -> this.useIt(s, start));
		
		
		System.out.println("----- Main thread finished -----");
		
		client.close();
	}
	
	private void useIt(String s, long start) {
		System.out.println("Entities from response:\n"+s+"\nthread: "+Thread.currentThread().getName());
		long end = System.currentTimeMillis() - start;
		System.out.println("Time: "+end+" ms");
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new ReactiveTestCombine();
	}
}