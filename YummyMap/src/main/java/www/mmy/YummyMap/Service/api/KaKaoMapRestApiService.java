package www.mmy.YummyMap.Service.api;

/**
 * 카카오맵 RestApi처리를 위해 제작되었습니다.
 * 
 * @ @author 김종형
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Service
public class KaKaoMapRestApiService {
	private String basePath = "https://dapi.kakao.com/v2/local/search/keyword.json?category_group_code=FD6";
	private String subwayPath = "https://dapi.kakao.com/v2/local/search/keyword.json?category_group_code=SW8";
	private String authorKey = "e457f7b2d3393084fafd19c71b0c5bed";
	enum KakaoMapCategoryCode {
		BASE, SUBWAY
	}

	
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
		URL pathUrl = setUrl(searchInfoVo, page, KakaoMapCategoryCode.BASE);
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
		} catch (Exception e) {
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
	
	public JsonObject searchSubway(String query_keyword) {
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = null;
		try {
			SearchInfoVO searchInfoVo = new SearchInfoVO();
			searchInfoVo.setQuery_keyword(query_keyword);
			URL url = setUrl(searchInfoVo, 1, KakaoMapCategoryCode.SUBWAY);
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
	
	public URL setUrl(SearchInfoVO searchInfoVo, int page, KakaoMapCategoryCode categoryCode) {
		URL pathUrl = null;
		StringBuffer path = new StringBuffer();
		String query;
		try {
			switch(categoryCode) {
			case BASE:
				query = URLEncoder.encode(searchInfoVo.getKeyword(),"UTF-8");
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
				break;
			case SUBWAY:
				query = URLEncoder.encode(searchInfoVo.getQuery_keyword(),"UTF-8");
				path.append(subwayPath);
				path.append("&query=");
				path.append(query);
				path.append("&page=");
				path.append(page);
				pathUrl = new URL(path.toString());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathUrl;
	}
	
	/*
	 * 카카오맵 RestAPI 파싱을통한 결과를 List로 반환합니다.
	 */
	public List<UpsoVO> getUpsoList(SearchInfoVO searchInfoVo) {
		List<UpsoVO> result_upsoList = new ArrayList<UpsoVO>();
		Gson gson = new Gson();
		boolean is_continue = true;
		int page = 1;
		do {
			JsonObject jsonObject = searchList(searchInfoVo, page);
			JsonArray jsonList = (JsonArray) jsonObject.get("documents");
			Type listType = new TypeToken<ArrayList<UpsoVO>>(){}.getType();
			List<UpsoVO> upsoList = gson.fromJson(jsonList.toString(), listType);
			for(int i=0; i<upsoList.size(); i++) {
				UpsoVO upsoVo = upsoList.get(i);
				result_upsoList.add(upsoVo);
			}
			// 검색결과 페이지가 더 있는지 조회합니다.
			is_continue = continueParsing(jsonObject);
			if(is_continue)
				page++;
		} while (is_continue);		
		return result_upsoList;
	}
	
	/*
	 * 검색결과 페이지가 더 있는지 조회합니다.
	 */
	public boolean continueParsing(JsonObject jsonObject) {
		boolean result = false;
		JsonElement is_end = jsonObject.getAsJsonObject("meta").get("is_end");
		if(is_end.toString().equals("false")) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public JsonObject getUpsoMetaObject(SearchInfoVO searchInfoVo) {
		JsonObject jsonObject = searchList(searchInfoVo, 1);
		JsonObject meta = jsonObject.getAsJsonObject("meta");
		return meta;
	}
	
	public int getCountSubwayStation(String query_keyword) {
		JsonObject jsonObject_subway = searchSubway(query_keyword);
		JsonObject meta_subway = jsonObject_subway.getAsJsonObject("meta");
		JsonElement total_count = meta_subway.get("total_count");
		int count = Integer.parseInt(total_count.toString());
		return count;
	}

}
