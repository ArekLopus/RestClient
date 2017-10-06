package alternative.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@SuppressWarnings("unused")
public class HttpURLConnectionGET {

	public HttpURLConnectionGET() throws Exception{
		
		//long start = System.currentTimeMillis();
		
		URL url = new URL("http://localhost:8080/TestWeb/res/test");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "text/html");
		
		long start = System.currentTimeMillis();
		
		if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("Response error "+conn.getResponseCode());
		} else {
			System.out.println("Response code 200");
			
			InputStream is = conn.getInputStream();
			
			//String text = readFromIS(is);
			String text = readFromIS2(is);
			
			System.out.println("Response: "+text);
		}
		
		long end = System.currentTimeMillis() - start;
		System.out.println("Time: "+end+" ms");

		conn.disconnect();
		
		System.out.println("----- Main thread finished -----");

	}
	
	
	private String readFromIS(InputStream is) {
		Scanner scanner = new Scanner(is);
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}
	
	private String readFromIS2(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader((is)));
		
		String output = null;
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) throws Exception {
		new HttpURLConnectionGET();
	}
}