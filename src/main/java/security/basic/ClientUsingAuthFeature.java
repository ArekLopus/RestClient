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
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("user", "pass");		//preemptive
		HttpAuthenticationFeature feature2 = HttpAuthenticationFeature.universal("user", "pass");
		HttpAuthenticationFeature feature3 = HttpAuthenticationFeature
				.basicBuilder()
				.nonPreemptive()
				.credentials("user", "pass")
				.build();
		
		
		Client client = ClientBuilder.newClient();
		client.register(feature3);
		
		
		WebTarget webTarget = client.target("http://localhost:8080/RestServer/res/secured/secured2");
		
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
