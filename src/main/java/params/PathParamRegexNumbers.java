package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/TestWeb/res/params/132
public class PathParamRegexNumbers {

	public PathParamRegexNumbers() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/TestWeb/res/params/{id : \\d+}");
		//WebTarget wt = client.target("http://localhost:8080/TestWeb/res/params/{id}");
		String st = wt
			.resolveTemplate("id", "1941")
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		
		try {
			WebTarget wt1 = client.target("http://localhost:8080/TestWeb/res/params/{id}");
			String st1 = wt1
				.resolveTemplate("id", "John1")
				.request(MediaType.TEXT_HTML)
				.get(String.class);
		
			System.out.println(st1);
			
		} catch(Exception e) {
			System.out.println("Excepttion if letters: "+e.getMessage());
		}
		
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new PathParamRegexNumbers();
	}
	
}
