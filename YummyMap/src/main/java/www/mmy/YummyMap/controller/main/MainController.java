package www.mmy.YummyMap.controller.main;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import www.mmy.YummyMap.Service.main.MainService;
import www.mmy.YummyMap.vo.UpSoVO;

@Controller
public class MainController {

	private MainService mainService;
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	@RequestMapping("/main.mmy")
	public String forwardMainListView() {
		return "main/mainList";
	}
	
	@RequestMapping("/main/getList.mmy")
	public ModelAndView searchList(ModelAndView mv, String keyword, String x, String y) {
		List<UpSoVO> upSoVoList = mainService.getSearchList(keyword, x, y);
		mv.addObject(upSoVoList);
		return mv;
	}


}
