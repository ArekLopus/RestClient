package abc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import utils.User;

@SuppressWarnings("unused")
public class GenericEntityTest {

	public GenericEntityTest() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target("http://localhost:8080/TestWeb/res/test/jsons").request().get();
		System.out.println(response);
		
		List<User> users = response.readEntity(new GenericType<List<User>>(){});
		System.out.println(users);
		
		//or
//		User[] users = response.readEntity(User[].class);
//		System.out.println(Arrays.asList(users));
		
		System.out.println("----- Main thread finished -----");
		
		client.close();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new GenericEntityTest();
	}
}