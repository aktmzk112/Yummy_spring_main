package www.mmy.YummyMap.controller.main;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import www.mmy.YummyMap.Service.main.MainService;
import www.mmy.YummyMap.util.PageUtil;
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
	public ModelAndView searchList(ModelAndView mv, SearchInfoVO searchInfoVo, PageUtil pageUtil) {
		searchInfoVo = mainService.analyzeKeyword(searchInfoVo);
		int count = searchInfoVo.getUpsoCount();
		if(count == 0) {
			mainService.setUpsoList(searchInfoVo);			
		} else {
			pageUtil.setTotalCount(count);
		}
		pageUtil.setPageRow(10);
		pageUtil.setPageGroup(5);
		pageUtil.totalfun();
		List<UpsoVO> upSoVoList = mainService.getUpsoList(searchInfoVo, pageUtil);
		mv.setViewName("main/mainSearchList");
		mv.addObject("upSoVoList",upSoVoList);
		mv.addObject("searchInfoVo",searchInfoVo);
		mv.addObject("pageUtil",pageUtil);
		return mv;
	}
	@RequestMapping("/main/getDetail.mmy")
	public ModelAndView getUpsoDetail(UpsoVO upsoVo, ModelAndView mv) {
		upsoVo = mainService.getUpsoDetail(upsoVo);
		mv.setViewName("main/mainDetail");
		mv.addObject("upsoVo",upsoVo);
		return mv;
	}
	
	@RequestMapping("/main/groupByCategory.mmy")
	public ModelAndView groupByCategory(ModelAndView mv, SearchInfoVO searchInfoVo) {
		List<UpsoVO> upSoVoList = mainService.getUpsoListGroupByCategory(searchInfoVo);
		mv.addObject("upSoVoList", upSoVoList);
		mv.addObject("searchInfoVo", searchInfoVo);
		mv.setViewName("main/mainSearchList");
		return mv;
	}
}
