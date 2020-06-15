package www.mmy.YummyMap.controller.main;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import www.mmy.YummyMap.Service.main.MainService;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Controller
public class MainController {

	private MainService mainService;
	
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	@RequestMapping("/main.mmy")
	public String forwardMainListView() {
		return "main/main";
	}
	
	@RequestMapping("/main/getList.mmy")
	public ModelAndView searchList(ModelAndView mv, SearchInfoVO searchInfoVo) {
		Map<String, Object> resultMap = mainService.getSearchList(searchInfoVo);
		List<UpsoVO> upSoVoList = (List<UpsoVO>) resultMap.get("upSoListReturnValue");
		searchInfoVo = (SearchInfoVO) resultMap.get("searchInfoVo");
		mv.setViewName("main/mainSearchList");
		mv.addObject("upSoVoList",upSoVoList);
		mv.addObject("searchInfoVo",searchInfoVo);
		return mv;
	}
	@RequestMapping("/main/getDetail.mmy")
	public ModelAndView getUpsoDetail(UpsoVO upsoVo, ModelAndView mv) {
		upsoVo = mainService.getUpsoDetail(upsoVo);
		mv.setViewName("main/mainDetail");
		mv.addObject("upsoVo",upsoVo);
		return mv;
	}
}
