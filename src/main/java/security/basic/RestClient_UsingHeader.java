package security.basic;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

public class RestClient_UsingHeader {

	public RestClient_UsingHeader() {
		
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/secured/secured2");
		
		byte[] encodedBytes = Base64.getEncoder().encode("arek:arek".getBytes(StandardCharsets.UTF_8));
		String encoded = new String(encodedBytes, StandardCharsets.UTF_8);
		
		String st = webTarget
			.request(MediaType.TEXT_HTML)
			//.header("Authorization", "Basic YXJlazphcmVr")	//encoded b64 - arek:arek
			.header(HttpHeaders.AUTHORIZATION, "Basic "+encoded)
			.get(String.class);
		
		System.out.println(""+encoded);
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new RestClient_UsingHeader();
	}
}
