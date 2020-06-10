package www.mmy.YummyMap.Service.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import www.mmy.YummyMap.api.KakaoMapRestApi;
import www.mmy.YummyMap.vo.UpSoVO;

@Service
public class MainService {

	private UpSoService upSoService; 
	
	public MainService(UpSoService upSoService) {
		this.upSoService = upSoService;
	}
	
	public List<UpSoVO> getSearchList(String keyword, String x, String y) {
		KakaoMapRestApi kakao = new KakaoMapRestApi();
		String resultJsonStr = kakao.searchList(keyword, x, y);
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(resultJsonStr);
		JsonArray jsonList = (JsonArray) jsonObject.get("documents");
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<UpSoVO>>(){}.getType();
		List<UpSoVO> upSolist = gson.fromJson(jsonList.toString(), listType);
		for(int i=0; i<upSolist.size(); i++) {
			UpSoVO upSoVo = upSolist.get(i);
			boolean isShow = upSoService.isShowUpSo(upSoVo.getId());
			if(isShow) {
				continue;
			}
			upSoService.insertUpSo(upSoVo);
		}
		return upSolist;
	}

}
