package www.mmy.YummyMap.controller.main;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.Service.main.MainService;
import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.util.ProjectUrl;
import www.mmy.YummyMap.vo.RatingUpsoVO;
import www.mmy.YummyMap.vo.ReviewVO;
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
		String category_name = searchInfoVo.getCategory_name();
		int count = 0;
		if(category_name == null) {
			count = searchInfoVo.getUpsoCount();
			if(count == 0) {
				mainService.setUpsoList(searchInfoVo);			
			} else {
				pageUtil.setTotalCount(count);
			}
		} else {
			count = mainService.upsoCount_group_category(searchInfoVo);
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
		RatingUpsoVO ratingVo = mainService.getRatingInfo(upsoVo.getId());
		List<ReviewVO> reviewList = mainService.getReviewList(upsoVo.getId());
		mv.setViewName("main/mainDetail");
		mv.addObject("upsoVo",upsoVo);
		mv.addObject("ratingVo",ratingVo);
		mv.addObject("reviewList",reviewList);
		return mv;
	}
	
	@RequestMapping("/main/reviewProcess.mmy")
	public ModelAndView reviewProcess(ModelAndView mv, ReviewVO reviewVo, HttpSession session, RedirectView redirect) {
		String userId = (String)session.getAttribute("SID");
		boolean result = mainService.insertReview(reviewVo, userId);
		String param = "?id="+reviewVo.getRes_id();
		redirect.setUrl(ProjectUrl.UPSO_DETAIL_VIEW.getUrl()+param);
		if(result == false) {
			
		}
		mv.setView(redirect);
		return mv;
	}
	
}
