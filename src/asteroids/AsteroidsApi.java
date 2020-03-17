package asteroids;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class AsteroidsApi {
	JSONObject near_earth_objects;
	int elementCount;
	
	public AsteroidsApi(String startDate, String endDate) throws Exception {

	     String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + startDate + "&end_date=" + endDate + "&api_key=o4YUysdb45f1PHxcx7ON7EhCXsLmcDVLXawL6R9D";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     //System.out.println("\nSending 'GET' request to URL : " + url);
	     //System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     //System.out.println(response.toString());
	     //Read JSON response and print
//	     JSONObject myResponse = new JSONObject(response.toString());
//	     JSONArray myResponse2 = myResponse.getJSONObject("near_earth_objects").getJSONArray("2020-02-20");
//	     JSONObject myResponse3 = myResponse2.getJSONObject(1);
	     //System.out.println(myResponse3.getJSONObject("links").toString();
	     //System.out.println(new JSONObject(response.toString()).getJSONObject("near_earth_objects").getJSONArray("2020-02-20").getJSONObject(0).getString("name"));
	     //System.out.println(myResponse);
	     //String name = new JSONObject(response.toString()).getJSONObject("near_earth_objects").getJSONArray("2020-02-20").getJSONObject(0).getString("name");
	     this.near_earth_objects = new JSONObject(response.toString()).getJSONObject("near_earth_objects");
	     this.elementCount = new JSONObject(response.toString()).getInt("element_count");
	     //System.out.println(elementCount);
	   }
	
	public String getName(String date, int id) throws JSONException {
		String name = near_earth_objects.getJSONArray(date).getJSONObject(id).getString("name").toString();		
		return name;
		
	}
	
	public String getCloseApproachDateFull(String date, int id) throws JSONException {
		String closeApproachDateFull = near_earth_objects.getJSONArray(date).getJSONObject(id).getJSONArray("close_approach_data").getJSONObject(0).getString("close_approach_date_full").toString();
		return closeApproachDateFull;
		
	}
	
	public String getDiameter(String date, int id) throws JSONException {
		int diameter = near_earth_objects.getJSONArray(date).getJSONObject(id).getJSONObject("estimated_diameter").getJSONObject("meters").getInt("estimated_diameter_max");	
		//System.out.println(diameter);
		String diameter_str = Integer.toString(diameter);		
		return diameter_str;
		
	}
	
	public String getMissDistance(String date, int id) throws JSONException {
		String missDistance = near_earth_objects.getJSONArray(date).getJSONObject(id).getJSONArray("close_approach_data").getJSONObject(0).getJSONObject("miss_distance").getString("kilometers");
		float miss_float = Float.parseFloat(missDistance);
		int miss_int = (int) miss_float;
		missDistance = Integer.toString(miss_int);
		//System.out.println(missDistance);		
		return missDistance;		
	}

	
	public int getDailyAsteroidsNumber(LocalDate date) throws JSONException {
		//System.out.println("Helooooo");
		//System.out.println(this.near_earth_objects.getJSONArray(date.toString()));
		return this.near_earth_objects.getJSONArray(date.toString()).length();
	}

}
