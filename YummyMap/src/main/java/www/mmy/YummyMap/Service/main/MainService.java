package www.mmy.YummyMap.Service.main;
/**
 * main 페이지에서 유저로부터 요청되는 다양한 기능을 구현한 클래스입니다.
 * 
 *  @author	김종형
 *  @see	www.mmy.YummyMap.Service.api.KaKaoMapRestApiService
 *  		www.mmy.YummyMap.dao.MainDAO
 *  		www.mmy.YummyMap.Service.main.UpSoServiceImpl
 */
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import www.mmy.YummyMap.Service.api.KaKaoMapRestApiService;
import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Service
public class MainService {

	private UpSoService upsoService;
	private MainDAO mainDao;
	private KaKaoMapRestApiService kakaoMapService;
	
	public MainService(UpSoService upsoService, MainDAO mainDao, KaKaoMapRestApiService kakaoMapService) {
		this.upsoService = upsoService;
		this.mainDao = mainDao;
		this.kakaoMapService = kakaoMapService;
	}
	
	public Map<String, Object> getSearchList(SearchInfoVO searchInfoVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int page = 1;
		int keywordCount = mainDao.isShowKeyword(searchInfoVo.getKeyword());
		JsonObject jsonObject = kakaoMapService.searchList(searchInfoVo, page);
		// 키워드 분석을 위한 데이터
		JsonObject meta = jsonObject.getAsJsonObject("meta");
		JsonElement selected_region = meta.getAsJsonObject("same_name").get("selected_region");
		JsonElement keyword = meta.getAsJsonObject("same_name").get("keyword");
		String query_location = selected_region.toString().replaceAll("\"", "");
		String query_keyword = keyword.toString().replaceAll("\"", "");
		System.out.println("query_keyword ::: " + query_keyword);
		System.out.println("query_location ::: " + query_location);
		System.out.println("query_location length ::: " + query_location.length());
		if(query_location.length() == 0) {
			JsonObject jsonObject_subway = kakaoMapService.searchSubway(query_keyword);
			JsonObject meta_subway = jsonObject_subway.getAsJsonObject("meta");
			JsonElement total_count = meta_subway.get("total_count");
			int count = Integer.parseInt(total_count.toString());
			if(count != 0) {
				searchInfoVo.setQuery_location(query_keyword);
			}
		}
		if(keywordCount == 0) {
			// kakaoMap에 해당 키워드로의 요청이 한번도 없는 경우
			Gson gson = new Gson();
			boolean is_continue = true;
			do {
				JsonArray jsonList = (JsonArray) jsonObject.get("documents");
				Type listType = new TypeToken<ArrayList<UpsoVO>>(){}.getType();
				List<UpsoVO> upSolist = gson.fromJson(jsonList.toString(), listType);
				for(int i=0; i<upSolist.size(); i++) {
					UpsoVO upSoVo = upSolist.get(i);
					upSoVo.setQuery_keyword(searchInfoVo.getKeyword());
					boolean isShow_upso = upsoService.isShowUpSo(upSoVo.getId());
					if(isShow_upso) {
						upsoService.insertKeyword(upSoVo);
						continue;
					}
					upsoService.insertUpSo(upSoVo);
					upsoService.insertKeyword(upSoVo);
				}
				// 검색결과 페이지가 더 있는지 조회합니다.
				JsonElement is_end = jsonObject.getAsJsonObject("meta").get("is_end");
				if(is_end.toString().equals("false")) {
					is_continue = true;
					page++;
					jsonObject = kakaoMapService.searchList(searchInfoVo, page);
				} else {
					is_continue = false;
				}
			} while (is_continue);
		}
		List<UpsoVO> upSoListReturnValue = mainDao.getUpSoList_keyword(searchInfoVo);
		resultMap.put("upSoListReturnValue", upSoListReturnValue);
		resultMap.put("searchInfoVo", searchInfoVo);

		return resultMap;
	}
	
	public UpsoVO getUpsoDetail(UpsoVO upsoVo) {
		upsoVo = upsoService.getUpSoDetailInfo(upsoVo.getId());
		return upsoVo;
	}

	public List<UpsoVO> getUpsoListGroupByCategory(SearchInfoVO searchInfoVo){
		List<UpsoVO> upsoList = mainDao.getUpSoList_groupByCategory(searchInfoVo);
		return upsoList;
	}
}
