package www.mmy.YummyMap.controller.main;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		// 키워드 분석메소드 호출
		List<UpsoVO> upsoList = mainService.doSearchAndGetList(searchInfoVo, pageUtil);
		// DB에서 조회된 업소들의 카테고리 리스트 가져오기
		List<String> categoryList = mainService.getCategoryList(searchInfoVo);
		
		mv.setViewName("main/mainSearchList");
		mv.addObject("upSoVoList",upsoList);
		mv.addObject("categoryList",categoryList);
		mv.addObject("searchInfoVo",searchInfoVo);
		mv.addObject("pageUtil",pageUtil);
		return mv;
	}
	
	/*
	 * 추상화 작업
	 */
	public ModelAndView searchList2(ModelAndView mv, SearchInfoVO searchInfoVo, PageUtil pageUtil) {
		// doParsingApi();
		// mainService.setPageUtil();
		// List<UpsoVO> upsoList = mainService.getUpsoList(SearchInfoVO searchInfoVo, PageUtil pageUtil);
		// List<String> categoryList = mainService.getCategoryList(searchInfoVo);
		
		mv.setViewName("main/mainSearchList");
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

	@RequestMapping( method = RequestMethod.POST, value = "/main/reviewProcess.mmy")
	public ModelAndView reviewProcess(ReviewVO reviewVo, ModelAndView mv,  HttpSession session, RedirectView redirect) {
		boolean result = mainService.insertReview(reviewVo, session);
		String param = "?id="+reviewVo.getRes_id();
		redirect.setUrl(ProjectUrl.UPSO_DETAIL_VIEW.getUrl()+param);
		
		mv.setView(redirect);
		return mv;
	}
	
	@RequestMapping("/kakaoLogout.mmy")
	public String kakaoLogout() {
		return "member/kakaoLogout";
	}
	
}
