package ver21.sse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

//	SseEventSource (AutoCloseable) - https://javaee.github.io/javaee-spec/javadocs/javax/ws/rs/sse/SseEventSource.html - Client side
//-Client for reading and processing incoming Server-Sent Events.
//-SSE event source instances of this class are thread safe.
//-To build a new instance, you can use the SseEventSource.target(endpoint) factory method to get a new event source builder
// that can be further customised and eventually used to create a new SSE event source.
//-Once a SseEventSource is created, it can be used to open a connection to the associated web target.
// After establishing the connection, the event source starts processing any incoming inbound events. 
//-Whenever a new event is received, an Consumer#accept(InboundSseEvent) method is invoked on any registered event consumers.

//Reconnect support
//-The SseEventSource supports automated recuperation from a connection loss, including negotiation of delivery of any missed events based
// on the last received SSE event id field value, provided this field is set by the server and the negotiation facility is supported by the server.
// In case of a connection loss, the last received SSE event id field value is send in the "Last-Event-ID" HTTP request header as part of a new connection request
// sent to the SSE endpoint. Upon a receipt of such reconnect request, the SSE endpoint that supports this negotiation facility is expected to replay
// all missed events. Note however, that this is a best-effort mechanism which does not provide any guaranty that all events would be delivered without a loss.
// You should therefore not rely on receiving every single event and design your client application code accordingly.
//-By default, when a connection the the SSE endpoint is lost, the event source will wait 500 ms before attempting to reconnect to the SSE endpoint.
// The SSE endpoint can however control the client-side retry delay by including a special retry field value in the any send event.
// JAX-RS SseEventSource tracks any received SSE event retry field values set by the endpoint and adjusts the reconnect delay accordingly,
// using the last received retry field value as the reconnect delay.
//-In addition to handling the standard connection loss failures, JAX-RS SseEventSource automatically deals with any HTTP 503 Service Unavailable
// responses from an SSE endpoint, that contain a "Retry-After" HTTP header with a valid value.
//-The HTTP 503 + "Retry-After" technique is often used by HTTP endpoints as a means of connection and traffic throttling.
// In case a HTTP 503 + "Retry-After" response is received in return to a connection request, JAX-RS SSE event source will automatically schedule
// a new reconnect attempt and use the received "Retry-After" HTTP header value as a one-time override of the reconnect delay.

//static SseEventSource.Builder target(WebTarget endpoint), void open(), boolean isOpen(), default void close(), boolean close(long timeout, TimeUnit unit)
//void register(Consumer<InboundSseEvent> onEvent), void register(Consumer<InboundSseEvent> onEvent, Consumer<Throwable> onError)
//void register(Consumer<InboundSseEvent> onEvent, Consumer<Throwable> onError, Runnable onComplete)


public class SseClient {

	public SseClient() throws InterruptedException {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/RestServer/res/sse");
		
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