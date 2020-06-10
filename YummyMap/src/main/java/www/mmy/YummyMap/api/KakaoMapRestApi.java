package www.mmy.YummyMap.api;
/**
 * 카카오맵 RestApi처리를 위해 제작되었다.
 * 
 * @ @author 김종형
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class KakaoMapRestApi {
	private String basePath = "https://dapi.kakao.com/v2/local/search/keyword.json?category_group_code=FD6";
	private String authorKey = "e457f7b2d3393084fafd19c71b0c5bed";
	
	/*
	 * parameter	: keyword:검색키워드, x:longitude, y:latitude
	 * return 		: json타입의 문자열
	 */
	public String searchList(String keyword, String x, String y) {
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = null;
		try {
			URL url = setUrl(keyword, x, y);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "KakaoAK "+ authorKey);
			System.out.println("[ Response Code ::: " + conn.getResponseCode() + " ]");
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}
			sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rd.close();
				conn.disconnect();
			} catch (Exception e2) {
			}
		}
		return sb.toString();
	}
	
	private URL setUrl(String keyword, String x, String y) {
		URL pathUrl = null;
		StringBuffer path = new StringBuffer();
		try {
			String query = URLEncoder.encode(keyword,"UTF-8");
			path.append(basePath);
			path.append("&query=");
			path.append(query);
			path.append("&x=");
			path.append(x);
			path.append("&y=");
			path.append(y);
			path.append("&radius=1000");
			pathUrl = new URL(path.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathUrl;
	}
	
	
	/*
	public static void main(String[] args) {
		try {
			String test = testRestApi();
			JsonObject jsonObject = (JsonObject) new JsonParser().parse(test);
			JsonArray jsonList = (JsonArray) jsonObject.get("documents");
			for(int i=0; i<jsonList.size(); i++) {
				System.out.println(jsonList.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String testRestApi() throws IOException {
		String path = "http://openAPI.guro.go.kr:8088/6564467a72626c753830585964474d/json/GuroFoodHygieneBizRestaurant/1/15/";
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String str = URLEncoder.encode("치킨", "UTF-8");
		conn.setRequestProperty("UPSO_NM", str);
		System.out.println("[ Response Code ::: " + conn.getResponseCode() + " ]");
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}
	public static void main(String[] args) {
		try {
			String test = testRestApi();
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(test);
			JsonObject json = (JsonObject) jsonObject.get("GuroFoodHygieneBizRestaurant");
			JsonArray array = (JsonArray) json.get("row");
			for(int i=0; i<array.size(); i++) {
				JsonObject row = (JsonObject) array.get(i);
				System.out.println(row.get("UPSO_NM"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
}
