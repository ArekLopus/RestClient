package timeout;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClientTimeoutBadAddress {
	
	@Rule
    public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void readTimeout() {
		
		expected.expect(ProcessingException.class);
		
		Client client = ClientBuilder.newBuilder()
			.connectTimeout(1, TimeUnit.MILLISECONDS)
			.build();

		client.target("http://localhost")
			.request()
			.get();
	}

}