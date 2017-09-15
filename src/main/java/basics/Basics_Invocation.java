package basics;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//-An invocation is a request that has been prepared and is ready for execution.
//-Invocations provide a generic (command) interface that enables a separation of concerns between the creator and the submitter.
//In particular, the submitter does not need to know how the invocation was prepared, but only how it should
//be executed (synchronously or asynchronously) and when.
public class Basics_Invocation {

	@SuppressWarnings("unused")
	public Basics_Invocation() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		//Building Invocation
		Builder invocation = client.invocation(Link.fromPath("http://localhost:8080/TestWeb/res/test").build()); 
		AsyncInvoker async = invocation.async();
		Future<Response> future = async.get();
		Future<String> future2 = async.get(String.class);
		
		Invocation buildGet = invocation.buildGet();
		Response response = invocation.get();
		String string = invocation.get(String.class);
		Response method = invocation.method("GET");
		String method2 = invocation.method("GET", String.class);
		
		Invocation buildGet2 = invocation
		    .accept(MediaType.TEXT_HTML)
		    .acceptLanguage(Locale.US)
		    .cookie("abc", "abc")
		    .header("MyHeader", "abc")
		    .buildGet();
		
		//Invoking Invocation
		Response invoke = buildGet2.invoke();
		String invoke2 = buildGet2.invoke(String.class);
		Future<Response> submit = buildGet2.submit();
		Future<String> submit2 = buildGet2.submit(String.class);
		
		AsyncInvoker async2 = invocation.async();
		async2.get(new InvocationCallback<Response>() {
			@Override
			public void completed(Response response) {
				System.out.println("Async response: "+response.readEntity(String.class)+", thread: "+Thread.currentThread().getName());
			}

			@Override
			public void failed(Throwable throwable) {
				System.out.println("Async failed");
			}
			
		});
		
		System.out.println(method2);
		System.out.println(future2.get());
				
		System.out.println("Main thread finished");
		
		Thread.sleep(500);
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new Basics_Invocation();
	}
}