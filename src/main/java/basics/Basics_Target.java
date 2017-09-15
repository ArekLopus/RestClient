package basics;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Basics_Target {

	@SuppressWarnings("unused")
	public Basics_Target() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/TestWeb/res/test");
		
		//We can set params for matrix, query, and path params
		WebTarget params = target.matrixParam("abc", "abc").queryParam("abc", "abc").resolveTemplate("abc", "abc");
		
		//After request() we build Invocation
		String string = params.request().get(String.class);
		
		Invocation buildGet = target.request()
		    .accept(MediaType.TEXT_HTML)
		    .cookie("abc","abc")
		    .header("abc", "abc")
		    .buildGet();
		
		Response invoke = buildGet.invoke();
		String invoke2 = buildGet.invoke(String.class);
		Future<Response> submit = buildGet.submit();
		Future<String> submit2 = buildGet.submit(String.class);
		
		AsyncInvoker async = target.request()
			.accept(MediaType.TEXT_HTML)
			.acceptLanguage(Locale.US)
			.cookie("abc","abc")
			.header("abc", "abc")
			.async();
		
		async.get(new InvocationCallback<Response>() {
			@Override
			public void completed(Response response) {
				System.out.println("Async response: "+response.readEntity(String.class)+", thread: "+Thread.currentThread().getName());
			}

			@Override
			public void failed(Throwable throwable) {
				System.out.println("Async failed");
			}
			
		});
		
		Future<Response> future = async.get();
		Future<String> future2 = async.get(String.class);
		
		System.out.println(string);
		System.out.println(invoke2);
		
		
		
		System.out.println("Main thread finished");
		
		Thread.sleep(500);
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new Basics_Target();
	}
}