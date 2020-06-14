package www.mmy.YummyMap.Service.api;

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

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import www.mmy.YummyMap.vo.SearchInfoVO;

@Service
public class KaKaoMapRestApiService {
	private String basePath = "https://dapi.kakao.com/v2/local/search/keyword.json?category_group_code=FD6";
	private String authorKey = "e457f7b2d3393084fafd19c71b0c5bed";
	
	/*
	 * parameter	: SearchInfoVO, page
	 * 					SearchInfoVO ::  필수 -> keyword 
	 * 									 선택 ->  x, y
	 * 										   (radius = 1000 [1km] 으로 기본 설정되어있음)
	 * 					page :: 필수 -> 응답결과 페이지 (최대 페이지 45)
	 * return 		: json타입의 문자열
	 */
	public JsonObject searchList(SearchInfoVO searchInfoVo, int page) {
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = null;
		try {
			URL url = setUrl(searchInfoVo, page);
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
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(sb.toString());
		return jsonObject;
	}
	
	
	private URL setUrl(SearchInfoVO searchInfoVo, int page) {
		URL pathUrl = null;
		StringBuffer path = new StringBuffer();
		try {
			String query = URLEncoder.encode(searchInfoVo.getKeyword(),"UTF-8");
			path.append(basePath);
			path.append("&query=");
			path.append(query);
			path.append("&page=");
			path.append(page);
			if(searchInfoVo.getX() != 0) {
				path.append("&x=");
				path.append(searchInfoVo.getX());
				path.append("&y=");
				path.append(searchInfoVo.getY());
				path.append("&radius=1000");
			}
			pathUrl = new URL(path.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathUrl;
	}

}