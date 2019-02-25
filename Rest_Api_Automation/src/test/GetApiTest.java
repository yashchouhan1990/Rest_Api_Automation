package test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.TestUtil;
import client.RestClient;
import base.TestBase;

public class GetApiTest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
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
	
	@Test(priority=1) 
	public void getApiTestWithoutHeaders() throws ClientProtocolException, IOException, JSONException{
		restClient =  new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---> : "+ statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b. Json String:
		String responeString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responeJson = new JSONObject(responeString);
		System.out.println("Respone Json from API---> "+ responeJson);
		
		//single value assertion--
		//per page:
		String perPageValue = TestUtil.getValueByJPath(responeJson, "/per_page");
		System.out.println("value of oer page page is -->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total:
		String totalValue = TestUtil.getValueByJPath(responeJson, "/total");
		System.out.println("Value of oer page page is -->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from the Json Array
		String lastName = TestUtil.getValueByJPath(responeJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responeJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responeJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responeJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//c. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue() );
		}
		System.out.println("Headers Array---> "+allHeaders);
	}
	
	@Test(priority=2)  
	public void getApiTestWithHeaders() throws ClientProtocolException, IOException, JSONException{
		restClient =  new RestClient();
		
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-type", "application/json");
		//headermap.put("lang", "eng");
		//headermap.put("auth token", "12345678");
		//headermap.put("username", "testing");
		//headermap.put("password", "testing123");

		
		closeableHttpResponse = restClient.get(url);
		
		//a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---> : "+ statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b. Json String:
		String responeString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responeJson = new JSONObject(responeString);
		System.out.println("Respone Json from API---> "+ responeJson);
		
		//single value assertion--
		//per page:
		String perPageValue = TestUtil.getValueByJPath(responeJson, "/per_page");
		System.out.println("value of oer page page is -->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total:
		String totalValue = TestUtil.getValueByJPath(responeJson, "/total");
		System.out.println("Value of oer page page is -->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from the Json Array
		String lastName = TestUtil.getValueByJPath(responeJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responeJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responeJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responeJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		//c. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue() );
		}
		System.out.println("Headers Array---> "+allHeaders);
	}
	
	
	
}
