package ver21.sse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

public class SseClient {

	public SseClient() throws InterruptedException {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/TestWeb/res/sse");
		try (SseEventSource source = SseEventSource.target(target).build()) {
		    source.register(System.out::println);
		    source.open();
		    
		    while(true) {
		    	Thread.sleep(10000);
		    }
		    
		} catch (Exception e) {
		    System.err.println("SseEventSource Failed: "+e.getMessage());
		}
		
		System.out.println("----- Main thread finished -----");
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new SseClient();
	}
}