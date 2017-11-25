package security.basic;

import java.security.MessageDigest;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class RestClient_UsingHeaderDigest {

	public RestClient_UsingHeaderDigest() {
		
		//HttpAuthenticationFeature feature = HttpAuthenticationFeature.universal("user", "pass");
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature
				.basicBuilder()
				.nonPreemptive()
				.credentials("user", "pass")
				.build();
		Client client = ClientBuilder.newClient();
		client.register(feature);
		
		
		WebTarget webTarget = client.target("http://localhost:8080/RestServer/res/secured/secured2");
		
		String st = webTarget
			.request(MediaType.TEXT_HTML)
			//.header("Authorization", "Basic dXNlcjpwYXNz")	//encoded b64 - user:pass
			//.header(HttpHeaders.AUTHORIZATION, )
			.get(String.class);
		
		//System.out.println(""+encoded);
		System.out.println(st);
		
		client.close();
	}
	
	
	private String abc() {
		java.security.SecureRandom random = null;
		String uri = "/res/secured/secured2";
		try {
			random = java.security.SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(System.currentTimeMillis()); 
			byte[] nonceBytes = new byte[16]; 
			random.nextBytes(nonceBytes); 

			String clientNonce = new String(Base64.getEncoder().encode(nonceBytes));
			System.out.println(clientNonce);
			System.out.println(new String(nonceBytes,"utf-8"));
			String serverNonce = "hHQVNuEdyZszjmEPwS/jkQ==";

			String HA1String = "arek:file-realm:arek";
			String HA2String = "GET:"+uri;
			byte[] ha1Bytes = HA1String.getBytes("UTF-8");
			byte[] ha2Bytes = HA2String.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			String HA1_1 = new String(md.digest(ha1Bytes)) + ":" + serverNonce + ":" + clientNonce;
			byte[] ha1_1 = HA1_1.getBytes("UTF-8");

			String HA1 = new String(md.digest(ha1_1));
			String HA2 = new String(md.digest(ha2Bytes));

			String HA1nonceHA2 = HA1+ "hHQVNuEdyZszjmEPwS/jkQ==" + HA2;
			byte[] ha1nonceha2bytes = HA1nonceHA2.getBytes("UTF-8");

			String responseVal = new String(md.digest(ha1nonceha2bytes),"utf-8");

			String response = "Digest "+
			                 "username=\"arek\",\r\n"+
			                 "realm=\"file-realm\",\r\n"+
			                 "nonce=\"" + serverNonce + "\",\r\n"+
			                 "uri=\"" + uri + "\",\r\n"+
			                 "qop=auth,\r\n"+
			                 "nc=00000001,\r\n"+ //Increment this each time.
			                 "cnonce=\"" + clientNonce + "\",\r\n"+
			                 "response=\"" + responseVal + "\"";

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static void main(String[] args) {
		new RestClient_UsingHeaderDigest();
	}
}
