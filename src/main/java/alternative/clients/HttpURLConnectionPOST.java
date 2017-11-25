package alternative.clients;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpURLConnectionPOST {

	public HttpURLConnectionPOST() throws Exception{
		
		URL url = new URL("http://localhost:8080/RestServer/res/test/json");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		String payload = "{\"name\": \"John\",\"surname\":\"Smith\"}";

		OutputStream os = conn.getOutputStream();
		os.write(payload.getBytes());
		os.flush();
		
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("Response error: "+conn.getResponseCode());
		} else {
			System.out.println("Response code 200");
			
			InputStream is = conn.getInputStream();
			
			String text = readFromIS(is);
			
			System.out.println("Response: "+text);
		}
		
		conn.disconnect();
		
		System.out.println("----- Main thread finished -----");

	}
	
	
	private String readFromIS(InputStream is) {
		Scanner scanner = new Scanner(is);
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}
	
	
	public static void main(String[] args) throws Exception {
		new HttpURLConnectionPOST();
	}
}