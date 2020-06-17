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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import www.mmy.YummyMap.Service.api.DaumSearchRestApiService;
import www.mmy.YummyMap.Service.api.KaKaoMapRestApiService;
import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Service
public class MainService {

	private UpSoService normalUpso;
	private MainDAO mainDao;
	@Autowired
	private KaKaoMapRestApiService kakaoMapService;
	@Autowired
	private DaumSearchRestApiService daumSearchService;
	
	public MainService(UpSoService upsoService, MainDAO mainDao) {
		this.normalUpso = upsoService;
		this.mainDao = mainDao;
	}
	
	// 검색 키워드를 분석해주는 메소드입니다.
	public SearchInfoVO analyzeKeyword(SearchInfoVO searchInfoVo) {
		SearchInfoVO tmp = mainDao.isShowKeyword(searchInfoVo.getKeyword());

		if(tmp == null) {
			searchInfoVo.setUpsoCount(0);
			// 키워드 분석을 위한 데이터
			JsonObject jsonObject = kakaoMapService.searchList(searchInfoVo, 1);
			JsonObject meta = jsonObject.getAsJsonObject("meta");
			JsonElement selected_region = meta.getAsJsonObject("same_name").get("selected_region");
			JsonElement keyword = meta.getAsJsonObject("same_name").get("keyword");
			String query_location = selected_region.toString().replaceAll("\"", "");
			String query_keyword = keyword.toString().replaceAll("\"", "");
			if(query_location.length() == 0) {
				JsonObject jsonObject_subway = kakaoMapService.searchSubway(query_keyword);
				JsonObject meta_subway = jsonObject_subway.getAsJsonObject("meta");
				JsonElement total_count = meta_subway.get("total_count");
				int count = Integer.parseInt(total_count.toString());
				if(count != 0) {
					searchInfoVo.setQuery_location(query_keyword);
				} else {
					searchInfoVo.setQuery_keyword(query_keyword);
				}
			} else {
				searchInfoVo.setQuery_location(query_location);
				if(query_keyword.length() != 0)
					searchInfoVo.setQuery_keyword(query_keyword);
			}
		} else {
			searchInfoVo = tmp;
		}
		if(searchInfoVo.getQuery_keyword() == null)
			searchInfoVo.setQuery_keyword("");
		if(searchInfoVo.getQuery_location() == null)
			searchInfoVo.setQuery_location("");
		return searchInfoVo;
	}
	
	/*
	 * REST API를 통해 업소 정보를 조회하는 메소드입니다.
	 * 해당 키워드로 업소 리스트를 요청한뒤, DB 저장 유무를 통해 새로운 업소 정보와 키워드 데이터를 저장합니다.
	 * parameter: analyzeKeyword()를 통해 분석된 SearchInfoVO [키워드]
	 */
	public void setUpsoList(SearchInfoVO searchInfoVo) {
		Gson gson = new Gson();
		boolean is_continue = true;
		int page = 1;
		do {
			JsonObject jsonObject = kakaoMapService.searchList(searchInfoVo, page);
			JsonArray jsonList = (JsonArray) jsonObject.get("documents");
			Type listType = new TypeToken<ArrayList<UpsoVO>>(){}.getType();
			List<UpsoVO> upSolist = gson.fromJson(jsonList.toString(), listType);
			int totalCount = upSolist.size();
			searchInfoVo.setUpsoCount(totalCount);
			for(int i=0; i<totalCount; i++) {
				UpsoVO upsoVo = upSolist.get(i);
				searchInfoVo.setUpso_id(upsoVo.getId());
				// 해당 업소가 기존에 DB에 저장되었는지 조회합니다.
				boolean isShow_upso = normalUpso.isShowUpSo(upsoVo.getId());
				if(isShow_upso) {
					// 뉴 키워드만 DB에 저장합니다.
					normalUpso.insertKeyword(searchInfoVo);
					continue;
				}
				// 이미지 정보를 요청해옵니다.
				setUpsoImage(upsoVo, searchInfoVo);
				normalUpso.insertUpSo(upsoVo);
				normalUpso.insertKeyword(searchInfoVo);
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
	
	/*
	 * 키워드에 해당하는 업소 리스트를 DB에서 조회합니다.
	 * 매개변수로는 analyzeKeyword() 를 통해 분석된 값을 전달합니다.
	 * parameter : SearchInfoVO
	 * return	 : List<UpsoVO>
	 */
	public List<UpsoVO> getUpsoList(SearchInfoVO searchInfoVo, PageUtil pageUtil) {
		List<UpsoVO> upSoList = mainDao.getUpSoList_keyword(searchInfoVo, pageUtil);
		return upSoList;
	}
	
	public UpsoVO getUpsoDetail(UpsoVO upsoVo) {
		upsoVo = normalUpso.getUpSoDetailInfo(upsoVo.getId());
		return upsoVo;
	}

	public List<UpsoVO> getUpsoListGroupByCategory(SearchInfoVO searchInfoVo){
		List<UpsoVO> upsoList = mainDao.getUpSoList_groupByCategory(searchInfoVo);
		return upsoList;
	}
	
	// 다음 RestAPI를 통해 이미지 url을 받아오는 메소드입니다.
	public UpsoVO setUpsoImage(UpsoVO upsoVo, SearchInfoVO searchInfoVo) {
		String keyword = searchInfoVo.getQuery_location() + " " + upsoVo.getPlace_name();
		JsonObject resultObj = daumSearchService.upsoImageSearch(keyword);
		String total_count = resultObj.getAsJsonObject("meta").get("total_count").toString().replace("\"","");
		int count = 0;
		if(total_count != null)
			count = Integer.parseInt(total_count);
		if(count != 0) {
			JsonArray jsonList = (JsonArray) resultObj.get("documents");
			JsonElement jsonElement =  jsonList.get(0);
			String image_url = jsonElement.getAsJsonObject().get("image_url").toString().replaceAll("\"", "");
			upsoVo.setImage_url(image_url);
		} else {
			upsoVo.setImage_url("/YummyMap/img/main/noimage.jpg");
		}
		return upsoVo;
	}
}
