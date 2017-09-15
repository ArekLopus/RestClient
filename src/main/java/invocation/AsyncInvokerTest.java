package invocation;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AsyncInvokerTest {

	public AsyncInvokerTest() throws InterruptedException, ExecutionException {
		
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/secured/secured2");
		
		byte[] encodedBytes = Base64.getEncoder().encode("arek:arek".getBytes(StandardCharsets.UTF_8));
		String encoded = new String(encodedBytes, StandardCharsets.UTF_8);
		
		Invocation inv = client.target("http://localhost:8080/TestWeb/res/secured/secured2")
			.request(MediaType.TEXT_HTML)
			.header(HttpHeaders.AUTHORIZATION, "Basic "+encoded)
			.buildGet();
		String abc = inv.invoke().readEntity(String.class);
		System.out.println(abc);
		
		AsyncInvoker asyncInv = client.target("http://localhost:8080/TestWeb/res/secured/secured2")
			.request(MediaType.TEXT_HTML)
			.header(HttpHeaders.AUTHORIZATION, "Basic "+encoded)
			.async();
		
		Future<Response> future = asyncInv.get();
		String abc2 = future.get().readEntity(String.class);
		System.out.println(abc2);
		
		Future<String> future2 = inv.submit(String.class);
		String abc3 = future2.get();
		System.out.println(abc3);
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new AsyncInvokerTest();
	}
}