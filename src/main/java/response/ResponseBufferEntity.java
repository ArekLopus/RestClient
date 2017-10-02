package response;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

//-Buffer the message entity data. 
//-In case the message entity is backed by an unconsumed entity input stream, all the bytes of the original entity IS are read and stored in a local buffer.
// The original entity input stream is consumed and automatically closed as part of the operation and the method returns true. 
//-In case the response entity instance is not backed by an unconsumed input stream an invocation of bufferEntity method is ignored and the method returns false. 
//-This operation is idempotent, i.e. it can be invoked multiple times with the same effect which also means that calling the bufferEntity()
// on an already buffered (and thus closed) message instance is legal and has no further effect.
// Also, the result returned by the bufferEntity() method is consistent across all invocations of the method on the same Response instance. 
//-Buffering the message entity data allows for multiple invocations of readEntity(...) methods on the response instance.
// Note however, that once the response instance itself is closed, the implementations are expected to release the buffered message entity data too.
// Therefore any subsequent attempts to read a message entity stream on such closed response will result in an IllegalStateException being thrown.
public class ResponseBufferEntity {

	public ResponseBufferEntity() {
		
		Client client = ClientBuilder.newClient();
		
		Response response = client.target("http://localhost:8080/TestWeb/res/test/response").request().get();
		
		System.out.println("response.getEntity(): "+response.getEntity());
		System.out.println("response.bufferEntity(): "+response.bufferEntity());
		
		System.out.println("response.getEntity(): "+response.getEntity());
		
		Response response2 = client.target("http://localhost:8080/TestWeb/res/streamingOutput/string").request().get();
		System.out.println("response.getEntity(): "+response2.getEntity());
		
		System.out.println("response.getEntity(): "+response.getEntity());
		client.close();
	}
	
	public static void main(String[] args) {
		new ResponseBufferEntity();
	}
	
}
