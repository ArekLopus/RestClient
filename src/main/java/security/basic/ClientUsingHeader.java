package security.basic;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

public class ClientUsingHeader {

	public ClientUsingHeader() {
		
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/RestServer/res/secured/secured2");
		
		byte[] encodedBytes = Base64.getEncoder().encode("user:pass".getBytes(StandardCharsets.UTF_8));
		String encoded = new String(encodedBytes, StandardCharsets.UTF_8);			//dXNlcjpwYXNz
		
		String st = webTarget
			.request(MediaType.TEXT_HTML)
			//.header("Authorization", "Basic dXNlcjpwYXNz")	//encoded b64 - user:pass
			.header(HttpHeaders.AUTHORIZATION, "Basic "+encoded)
			.get(String.class);
		
		System.out.println("Encoded: "+encoded);
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new ClientUsingHeader();
	}
}
