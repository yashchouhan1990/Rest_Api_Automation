package client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {

	// 1. Get Method
	public void get(String url) throws ClientProtocolException, IOException, JSONException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);  //http get request
		CloseableHttpResponse closeableHttpResponse  = httpClient.execute(httpget);  // hit the GET Url
		
		//1. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Statuc Code---> : "+ statusCode);
		
		//b. Json String:
		String responeString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responeJson = new JSONObject(responeString);
		System.out.println("Respone Json from API---> "+ responeJson);
		
		//c. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue() );
		}
		System.out.println("Headers Array---> "+allHeaders);
		
	}
}
