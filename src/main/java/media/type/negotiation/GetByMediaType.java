package media.type.negotiation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
public class GetByMediaType {

	public GetByMediaType() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget wt = client.target("http://localhost:8080/RestServer/res/test/jsonOrXml");
		Response resJson = wt.request(MediaType.APPLICATION_JSON).get();
		Response resXml = wt.request(MediaType.APPLICATION_XML).get();
		
		Response res1 = wt.request().header("Accept", "application/json").get();
		Response res2 = wt.request().header("Accept", "application/xml").get();
		
		//Each media-range MAY be followed by one or more accept-params, beginning with the "q" parameter for indicating a relative quality factor.
		//The first "q" parameter (if any) separates the media-range parameter(s) from the accept-params.
		//Quality factors allow the user or user agent to indicate the relative degree of preference for that media-range,
		// using the qvalue scale from 0 to 1 (section 3.9). The default value is q=1.
		//Accept: audio/*; q=0.2, audio/basic
		//SHOULD be interpreted as "I prefer audio/basic, but send me any audio type if it is the best available after an 80% mark-down in quality."
		Response res3 = wt.request().header("Accept", "application/json; q=0.4, application/xml; q=0.6").get();
		Response res4 = wt.request().header("Accept", "application/xml; q=0.8, application/json").get();	//json has 1.0 here
		
		System.out.println(resJson.readEntity(String.class));
		System.out.println(resXml.readEntity(String.class));
		
		System.out.println(res1.readEntity(String.class));
		System.out.println(res2.readEntity(String.class));
		
		System.out.println(res3.readEntity(String.class));
		System.out.println(res4.readEntity(String.class));
		
		client.close();
	}
	
	public static void main(String[] args) {
		new GetByMediaType();
	}
	
}
