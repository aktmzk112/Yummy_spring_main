package www.mmy.YummyMap.Service.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import www.mmy.YummyMap.vo.SearchInfoVO;

@Service
public class DaumSearchRestApiService {
	private String authorKey = "e457f7b2d3393084fafd19c71b0c5bed";
	private String basePath = "https://dapi.kakao.com/v2/search/";
	enum DaumCategoryCode {
		IMAGE
	}
	
	public JsonObject upsoImageSearch(String place_name) {
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = null;
		URL pathUrl = setUrl(place_name, 1, DaumCategoryCode.IMAGE);
		try {
			conn = (HttpURLConnection) pathUrl.openConnection();
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rd.close();
				conn.disconnect();
			} catch (Exception e2) {}
		}
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(sb.toString());
		return jsonObject;
	}
	

	public URL setUrl(String place_name, int page, DaumCategoryCode categoryCode) {
		URL pathUrl = null;
		StringBuffer path = new StringBuffer();
		String query;
		try {
			switch(categoryCode) {
			case IMAGE:
				query = URLEncoder.encode(place_name,"UTF-8");
				path.append(basePath);
				path.append("image?query=");
				path.append(query);
				pathUrl = new URL(path.toString());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathUrl;
	}
}
