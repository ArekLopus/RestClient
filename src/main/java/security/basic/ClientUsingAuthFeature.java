package security.basic;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

//Preemptive authentication - information is send always with each HTTP request.
//Non-preemptive - when server refuses the request with 401 status code the request is repeated with authentication information.
public class ClientUsingAuthFeature {

	@SuppressWarnings("unused")
	public ClientUsingAuthFeature() {
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("arek", "arek");		//preemptive
		HttpAuthenticationFeature feature2 = HttpAuthenticationFeature.universal("arek", "arek");
		HttpAuthenticationFeature feature3 = HttpAuthenticationFeature
				.basicBuilder()
				.nonPreemptive()
				.credentials("arek", "arek")
				.build();
		
		
		Client client = ClientBuilder.newClient();
		client.register(feature3);
		
		
		WebTarget webTarget = client.target("http://localhost:8080/TestWeb/res/secured/secured2");
		
		String st = webTarget
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	public static void main(String[] args) {
		new ClientUsingAuthFeature();
	}
}
