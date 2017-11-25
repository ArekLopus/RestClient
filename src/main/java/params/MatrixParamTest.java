package params;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/TestWeb/res/params/matrix;num1=123;num2=234;num3=345
public class MatrixParamTest {

	public MatrixParamTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/params/matrix");
		String st = wt
			.matrixParam("num1", "123")
			.matrixParam("num2", "234")
			.matrixParam("num3", "345")
			.request(MediaType.TEXT_HTML)
			.get(String.class);
		
		System.out.println(st);
		
		client.close();
	}
	
	
	public static void main(String[] args) {
		new MatrixParamTest();
	}
	
}
