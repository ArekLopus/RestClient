package invocation;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

//-An invocation is a REQUEST that has been PREPARED and is READY for execution.
//-Invocations provide a generic (command) interface that enables a separation of concerns between the creator and the submitter.
// In particular, the submitter does not need to know how the invocation was prepared, but only how it should
// be executed (synchronously or asynchronously) and when.
public class InvocationTest {

	public InvocationTest() throws InterruptedException, ExecutionException, UnsupportedEncodingException {
		
		
		Client client = ClientBuilder.newClient();
		
		String encoded = getEncoded("user:pass", StandardCharsets.UTF_8);
		
		//secured2 only needs authentication, no session needed.  
		Invocation inv = client.target("http://localhost:8080/RestServer/res/secured/secured2")
			.request(MediaType.TEXT_HTML)
			.header(HttpHeaders.AUTHORIZATION, "Basic "+encoded)
			.buildGet();
		String abc = inv.invoke().readEntity(String.class);
		
		System.out.println("Encoded: "+encoded);
		System.out.println(abc);
		
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, UnsupportedEncodingException {
		new InvocationTest();
	}
	
	private String getEncoded(String toEncode, Charset charSet) throws UnsupportedEncodingException {
		byte[] encodedBytes = Base64.getEncoder().encode(toEncode.getBytes(charSet));
		return new String(encodedBytes, charSet);
	}
}