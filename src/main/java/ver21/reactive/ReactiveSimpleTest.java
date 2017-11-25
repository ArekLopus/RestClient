package ver21.reactive;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

//-Using InvocationCallback enables a more reactive programming style in which user-provided code activates (or reacts) only when a certain event has occurred.
// Using callbacks works well for simple cases, but the source code becomes harder to understand when multiple events are in play. 
// Fe, when asynchronous invocations need to be composed, combined or in any way operated upon.
// These type of scenarios may result in callbacks that are nested inside other callbacks making the code far less readable,
// often referred to as the pyramid of doom because of the inherent nesting of calls.
//-To address the requirement of greater readability and to enable programmers to better reason about asynchronous computations,
// Java 8 introduces the a new interface CompletionStage that includes a large number of methods dedicated to managing async computations.
//-JAX-RS 2.1 defines a new type of invoker called RxInvoker, as well a default implementation of this type CompletionStageRxInvoker
//that is based on the Java 8 type CompletionStage (CompletableFuture).

//-There is a new rx() which is used in a similar manner to async()
public class ReactiveSimpleTest {

	public ReactiveSimpleTest() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		long start = System.currentTimeMillis();
		
		CompletionStage<Response> completionStage = client.target("http://localhost:8080/RestServer/res/reactive/react1").request().rx().get();
		
		completionStage.thenAcceptAsync(r -> this.useIt(r, start, client));
		
		
		System.out.println("----- Main thread finished -----");
		
		
	}
	
	private void useIt(Response res, long start, Client client) {
		System.out.println("Request response: "+res+",\nEntity from response: "+res.readEntity(String.class));
		long end = System.currentTimeMillis() - start;
		System.out.println("Time: "+end+" ms, thread: "+Thread.currentThread().getName());
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new ReactiveSimpleTest();
	}
}