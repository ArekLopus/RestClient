package ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//https://stackoverflow.com/a/41947084
public class ClientUsingSSL {

	public ClientUsingSSL() throws NoSuchAlgorithmException, KeyManagementException {
		
		TrustManager[] tm = new TrustManager[] {
			new X509TrustManager() {
    
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
                }
    
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
    
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
            }
        };
		
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, tm , null);
		
		
		Client client = ClientBuilder.newBuilder()
			.sslContext(sc)
			.hostnameVerifier((host, session) -> true)
			.build();

		WebTarget webTarget = client.target("https://localhost:8181/RestServer/res/ssl");
		
		String st = webTarget
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		new ClientUsingSSL();
	}
}
