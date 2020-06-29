package www.mmy.YummyMap.controller.main;



import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ModelAndView mainTempView(ModelAndView mv) {
		mv.setViewName("main/mainRedirect");
		return mv ; 
	}
	@RequestMapping("/index.mmy")
	public ModelAndView forwardMainListView(ModelAndView mv, SearchInfoVO searchInfoVo, HttpSession session) {
		List<UpsoVO> weeklyUpsoList = mainService.getWeeklyUpso();
		List<UpsoVO> myPickUpsoList = mainService.getMyUpsoList(searchInfoVo, session);
		mv.setViewName("main/main");
		mv.addObject("weeklyUpsoList", weeklyUpsoList);
		mv.addObject("myPickUpsoList", myPickUpsoList);
		mv.addObject("searchInfoVo", searchInfoVo);
		return mv ;
	}
	
	@RequestMapping("/main/mainRedirect.mmy")
	public ModelAndView redirectMainListView(ModelAndView mv, RedirectView rv, SearchInfoVO searchInfoVo) {
		String param = "?x="+searchInfoVo.getX()+"&y="+searchInfoVo.getY();
		rv.setUrl(ProjectUrl.MAIN_VIEW.getUrl()+param);
		mv.setView(rv);
		return mv ;
	}
	
	/*
	 * 사용자로부터 검색을 요청받았을때 요청 정보를 받아주는 컨트롤러입니다.
	 * param : 
	 * 		SearchInfoVO : 검색어를 담고, 키워드 분석 정보 데이터를 관리하는 DTO
	 * 		PageUtil	 : 페이징 처리를 위한 유틸
	 */
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
	 * 업소의 디테일 화면으로 전환해주는 컨트롤러입니다.
	 * param :
	 * 		UpsoVO : 업소 정보 데이터를 관리하는 DTO
	 */
	@RequestMapping("/main/getDetail.mmy")
	public ModelAndView getUpsoDetail(UpsoVO upsoVo, ModelAndView mv, HttpSession session) {
		upsoVo = mainService.getUpsoDetail(upsoVo, session);
		RatingUpsoVO ratingVo = mainService.getRatingInfo(upsoVo.getId());
		List<ReviewVO> reviewList = mainService.getReviewList(upsoVo.getId());
		mv.setViewName("main/mainDetail");
		mv.addObject("upsoVo",upsoVo);
		mv.addObject("ratingVo",ratingVo);
		mv.addObject("reviewList",reviewList);
		return mv;
	}

	/*
	 * 사용자로부터 입력받은 리뷰를 프로세스로 넘겨주는 컨트롤러입니다.
	 * param :
	 * 		ReviewVO : 리뷰 정보 데이터를 관리하는 DTO
	 */
	@RequestMapping( method = RequestMethod.POST, value = "/main/reviewProcess.mmy")
	public ModelAndView reviewProcess(ReviewVO reviewVo, ModelAndView mv,  HttpSession session, RedirectView redirect) {
		boolean result = mainService.insertReview(reviewVo, session);
		String param = "?id="+reviewVo.getRes_id();
		redirect.setUrl(ProjectUrl.UPSO_DETAIL_VIEW.getUrl()+param);
		
		mv.setView(redirect);
		return mv;
	}
	
	/*
	 * 업소 픽/언픽 요청을 처리하는 컨트롤러입니다.
	 * 요청과 응답은 비동기로 처리됩니다.
	 */
	@ResponseBody
	@RequestMapping("/main/pick.mmy")
	public Map<String, Boolean> myUpsoPickController(HttpSession session, int upso_id) {
		boolean result = mainService.upsoPickProcess(session, upso_id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("result", result);
		return response;
		
	}
	
	@RequestMapping("/kakaoLogout.mmy")
	public String kakaoLogout() {
		return "member/kakaoLogout";
	}
	
}
