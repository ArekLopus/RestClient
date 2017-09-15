package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/TestWeb/res/params/John
public class PathParamRegexLetters {

	public PathParamRegexLetters() {
		
		Client client = ClientBuilder.newClient();
		
		//WebTarget wt = client.target("http://localhost:8080/TestWeb/res/params/{name: [a-zA-Z]*}");
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/params/{name}");
		String st = wt
			.resolveTemplate("name", "John")
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		
		try {
			WebTarget wt1 = client.target("http://localhost:8080/TestWeb/res/params/{name: [a-zA-Z]*}");
			String st1 = wt1
				.resolveTemplate("name", "John1")
				.request(MediaType.TEXT_HTML)
				.get(String.class);
		
			System.out.println(st1);
			
		} catch(Exception e) {
			System.out.println("Excepttion if numbers: "+e.getMessage());
		}
		
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new PathParamRegexLetters();
	}
}
