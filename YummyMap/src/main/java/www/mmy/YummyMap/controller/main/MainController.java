package www.mmy.YummyMap.controller.main;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import www.mmy.YummyMap.api.KakaoMapRestApi;

@Controller
public class MainController {
	
	@RequestMapping("/main.mmy")
	public String forwardMainListView() {
		
		return "main/mainList";
	}
	
	@RequestMapping("/main/getList.mmy")
	public ModelAndView searchList(ModelAndView mv, String keyword, String x, String y) {
		System.out.println(x);
		System.out.println(y);
		KakaoMapRestApi kakao = new KakaoMapRestApi();
		String test = kakao.searchList(keyword, x, y);
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(test);
		JsonArray jsonList = (JsonArray) jsonObject.get("documents");
		for(int i=0; i<jsonList.size(); i++) {
			System.out.println(jsonList.get(i));
		}
		return mv;
	}


}
