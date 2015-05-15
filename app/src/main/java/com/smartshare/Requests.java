package com.smartshare;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Requests {

	public static final String SEARCH_NEARBY_ITEMS_URL=
			Constants.DOMAIN_NAME+"SmartShare/SearchNearbyItems";

	public static final String GET_ALL_NEARBY_ITEMS_URL=
			Constants.DOMAIN_NAME+"SmartShare/GetAllNearbyItems";

	public static final String ON_LOGIN_URL=
			Constants.DOMAIN_NAME+"SmartShare/OnLogin";

	public static final String ADD_ITEMS_URL=
			Constants.DOMAIN_NAME+"SmartShare/AddItem";

	public static final String GET_INVENTORY_ITEMS_URL=
			Constants.DOMAIN_NAME+"SmartShare/GetInventoryItems";

	public static final String REGISTER_NEW_USER_URL=
			Constants.DOMAIN_NAME+"SmartShare/GetNearbyItems";

	public static final String REQUEST_TRANSACTION_URL=
			Constants.DOMAIN_NAME+"SmartShare/GetNearbyItems";

	public static final String RESPOND_TO_TRANSACTION_URL=
			Constants.DOMAIN_NAME+"SmartShare/GetNearbyItems";

	public static final String SET_CURRENT_LOCATION=
			Constants.DOMAIN_NAME+"SmartShare/GetNearbyItems";


	//Generate a GET request string from params
	public static String generateGetRequest(List<GetParameter> listOfParameters){

		String paramString="?";
		for(GetParameter currentParameter:listOfParameters){
			paramString+=currentParameter.getKey()+"="+currentParameter.getValue()+"&";
		}

		return paramString;
	}

	//Get user info if the user exists or return null
	public static UserDetails getUserInfoIfExists(String facebookId) throws IOException, InstantiationException, IllegalAccessException{

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;

		// Will contain the raw JSON response as a string.
		String JsonStr = null;

		List<GetParameter> listOfParams=new ArrayList<GetParameter>();
		listOfParams.add(new GetParameter("facebookId", facebookId));
		URL url = new URL(ON_LOGIN_URL+generateGetRequest(listOfParams));
		urlConnection = (HttpURLConnection) url.openConnection();

		InputStream inputStream = urlConnection.getInputStream();
		StringBuffer buffer = new StringBuffer();
		if (inputStream == null) {
			return null;

		}
		reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;

		while ((line = reader.readLine()) != null) {
			buffer.append(line + "\n");
		}
		JsonStr = buffer.toString();

		if(!JsonStr.equals("null"))
			return (UserDetails) jsonToGenericObject(JsonStr, UserDetails.class);
		else return null;

	}

	//Get all items in the inventory of a user
	//Get all nearby items when a user search for an item
	public static List<Item> getInventoryItems(
			String facebookId) throws ClientProtocolException, IOException, URISyntaxException{

		HttpClient client = HttpClientBuilder.create().build();
		URIBuilder builder = new URIBuilder(GET_INVENTORY_ITEMS_URL);
		builder.setParameter("facebookId", facebookId);

		HttpGet request = new HttpGet(builder.build());		
		HttpResponse response = client.execute(request);

		//Debug.message(response.getEntity().getContent());
		BufferedReader rd = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent()));

		String jsonString = rd.readLine();
		//Debug.message("jsonString is "+jsonString);
		ObjectMapper mapper = new ObjectMapper();
		List<Item> listOfNearbyItems = mapper.readValue(jsonString,
				new TypeReference<ArrayList<Item>>() {});

		return listOfNearbyItems;

	}



	//Get all nearby items when a user search for an item
	public static List<Item> getAllNearbyItems(String facebookId) 
			throws ClientProtocolException, IOException, URISyntaxException{

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;

		// Will contain the raw JSON response as a string.
		String JsonStr = null;

		List<GetParameter> listOfParams=new ArrayList<GetParameter>();
		listOfParams.add(new GetParameter("facebookId", facebookId));

		//URL url = new URL(GET_ALL_NEARBY_ITEMS_URL+generateGetRequest(listOfParams));
        URL url = new URL("https://api.myjson.com/bins/4klj9");
		urlConnection = (HttpURLConnection) url.openConnection();

		InputStream inputStream = urlConnection.getInputStream();
		StringBuffer buffer = new StringBuffer();
		if (inputStream == null) {
			return null;

		}
		reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;

		while ((line = reader.readLine()) != null) {
			buffer.append(line + "\n");
		}
		JsonStr = buffer.toString();

		//String jsonString = rd.readLine();

		ObjectMapper mapper = new ObjectMapper();
		List<Item> listOfNearbyItems = mapper.readValue(JsonStr,
				new TypeReference<ArrayList<Item>>() {});

		return listOfNearbyItems;


	}

	//Get all nearby items when a user search for an item
	public static List<Item> searchNearbyItems(String itemName,String facebookId) 
			throws ClientProtocolException, IOException, URISyntaxException{

		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;

		// Will contain the raw JSON response as a string.
		String JsonStr = null;

		List<GetParameter> listOfParams=new ArrayList<GetParameter>();
		listOfParams.add(new GetParameter("facebookId", facebookId));
		listOfParams.add(new GetParameter("itemName", itemName));

		//URL url = new URL(SEARCH_NEARBY_ITEMS_URL+generateGetRequest(listOfParams));
        URL url = new URL("https://api.myjson.com/bins/4klj9");

        urlConnection = (HttpURLConnection) url.openConnection();
        Log.d("It Connects","Check");

		InputStream inputStream = urlConnection.getInputStream();
		StringBuffer buffer = new StringBuffer();
		if (inputStream == null) {
			return null;

		}
		reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;

		while ((line = reader.readLine()) != null) {
			buffer.append(line + "\n");
		}
		JsonStr = buffer.toString();

		//String jsonString = rd.readLine();

		ObjectMapper mapper = new ObjectMapper();
		List<Item> listOfNearbyItems = mapper.readValue(JsonStr,
				new TypeReference<ArrayList<Item>>() {});

		return listOfNearbyItems;


	}

	//Converts a JSON string to the required POJO(Object reconstruction)
	public static Object jsonToGenericObject(String jsonString,Class className) 
			throws InstantiationException, IllegalAccessException {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Object ob = className.newInstance();

			Object genericObject = objectMapper.readValue(jsonString, ob.getClass());
			return genericObject;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
