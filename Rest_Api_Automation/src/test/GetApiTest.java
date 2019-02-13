package test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import client.RestClient;
import base.TestBase;

public class GetApiTest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException, JSONException{
		testBase =  new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		
		url= serviceUrl + apiUrl;
		System.out.println("serviceUrl :" + serviceUrl);
		System.out.println("apiUrl :" + apiUrl);
		System.out.println("URL :" + url);
	}
	
	@Test ()
	public void getApiTest() throws ClientProtocolException, IOException, JSONException{
		restClient =  new RestClient();
		restClient.get(url);
	}
}
