package multipart.file;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ReadFileFromEndpoint {

	public ReadFileFromEndpoint() throws InterruptedException, ExecutionException {
		
		Client client = ClientBuilder.newClient();
		
		
		String string = client.target("http://localhost:8080/TestWeb/res/file/StreamingOut").request().get(String.class);
		System.out.println("Downloaded file size: "+string.length());
		//System.out.println("Downloaded file: "+string);
		
		InputStream is = client.target("http://localhost:8080/TestWeb/res/file/readTxt").request().get(InputStream.class);
		Scanner sc = new Scanner(is);
		System.out.println("First line: " + sc.nextLine());

		Response response = client.target("http://localhost:8080/TestWeb/res/file/readTxt2").request().get();
		System.out.println("Request response: "+response);
		System.out.println("Request response: "+response.getMediaType());
		System.out.println("Request response: "+response.getDate());
		System.out.println("Request response: "+response.getHeaders());
		System.out.println("Request response: "+response.hasEntity());
		
		
		System.out.println("Main thread finished");
		
		sc.close();
		client.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new ReadFileFromEndpoint();
	}
}