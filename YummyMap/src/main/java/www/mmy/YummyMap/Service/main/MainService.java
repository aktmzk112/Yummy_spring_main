package www.mmy.YummyMap.Service.main;
/**
 * main 페이지에서 유저로부터 요청되는 다양한 기능을 구현한 클래스입니다.
 * 
 *  @author	김종형
 *  @see	www.mmy.YummyMap.Service.api.KaKaoMapRestApiService
 *  		www.mmy.YummyMap.dao.MainDAO
 *  		www.mmy.YummyMap.Service.main.UpSoServiceImpl
 */
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.ImageFileVO;
import www.mmy.YummyMap.vo.RatingUpsoVO;
import www.mmy.YummyMap.vo.ReviewVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Service
public class MainService {

	private UpsoService upsoService;
	private MainDAO mainDao;
	@Autowired
	private FileService fileService;
	@Autowired
	private KeywordService keywordService; 
	
	
	public MainService(ParsingUpsoService upsoService, MainDAO mainDao) {
		this.upsoService = upsoService;
		this.mainDao = mainDao;
	}
	
	/*
	 * 분석된 키워드의 결과를 토대로 업소 리스트를 조회합니다.
	 * 조회 결과는 페이징처리되며, 페이징처리 조건은 PageUtil을 통하여 설정 할 수 있습니다.
	 */
	public List<UpsoVO> doSearchAndGetList(SearchInfoVO searchInfoVo, PageUtil pageUtil) {
		searchInfoVo = keywordService.analyzeKeyword(searchInfoVo);
		/*
		 *  만약 해당키워드로의 검색요청이 최초일 경우 api파싱과 db저장을 진행한다.
		 *  insertUpso() 매개변수로 UpsoVO, SearchInfoVO 를 입력한다.
		 *  매개변수 중 UpsoVO가 null일 경우 자동적으로 파싱 작업이 이루어진다. 
		 */
		if(searchInfoVo.isFirst()) {
			upsoService.insertUpso(null, searchInfoVo);
		}
		setPageUtil(pageUtil, searchInfoVo);
		List<UpsoVO> upsoList = upsoService.getUpsoList(searchInfoVo, pageUtil);
		return upsoList;
	}
	
	private void setPageUtil(PageUtil pageUtil, SearchInfoVO searchInfoVo) {
		int totalCount = searchInfoVo.getUpsoCount();
		pageUtil.setTotalCount(totalCount);
		pageUtil.setPageRow(10);
		pageUtil.setPageGroup(5);
		pageUtil.totalfun();
	}
	
	
	public List<String> getCategoryList(SearchInfoVO searchInfoVo) {
		String keyword = searchInfoVo.getKeyword();
		List<String> categoryList = mainDao.getCategoryList(keyword);
		int countCategory = categoryList.size();
		if(countCategory > 1) {
			searchInfoVo.setCategory_filtering("Y");
		} else {
			searchInfoVo.setCategory_filtering("N");
		}
		return categoryList;
	}
	
	public UpsoVO getUpsoDetail(UpsoVO upsoVo, HttpSession session) {
		String user_id = getUserIdInSession(session);
		upsoVo = upsoService.getUpsoDetailInfo(upsoVo.getId(), user_id);
		return upsoVo;
	}

	
	public int upsoCount_group_category(SearchInfoVO searchInfoVo) {
		int result = 0;
		result = mainDao.countUpso_category(searchInfoVo);
		return result;
	}
	
	/*
	 * 업소의 리뷰 분석 정보를 가져오는 메소드입니다.
	 */
	public RatingUpsoVO getRatingInfo(int upso_id) {
		if(upso_id == 0) {
			return new RatingUpsoVO();
		}
		RatingUpsoVO ratingVo = upsoService.getRatingInfo(upso_id);
		return ratingVo;
	}
	
	/*
	 * 업소에 등록된 리뷰 정보 리스트를 가져옵니다.
	 */
	public List<ReviewVO> getReviewList(int upso_id){
		if(upso_id == 0) {
			return new ArrayList<ReviewVO>();
		}
		List<ReviewVO> reviewList = upsoService.getReviewList(upso_id);
		for(int i=0; i < reviewList.size(); i++) {
			ReviewVO reviewVo = reviewList.get(i);
			int rev_no = reviewVo.getRev_no();
			List<ImageFileVO> imageList = upsoService.getReviewImgList(rev_no);
			reviewVo.setImgList(imageList);
		}
		return reviewList;
	}
	 
	/*
	 * 업소 리뷰를 저장해주는 메소드입니다.
	 * return : 성공 - true, 실패 - false
	 */
	public boolean insertReview(ReviewVO reviewVo, HttpSession session) {
		int insertCount = 0;
		String userId = getUserIdInSession(session);
		if(userId.equals(""))
			return false;
		reviewVo.setMid(userId);
		boolean result = upsoService.insertReview(reviewVo);
		if(result) {
			List<ImageFileVO> imageFileVoList = fileService.uploadProcess(session, reviewVo.getReviewImgFile());
			int rev_no = reviewVo.getRev_no();
			int res_no = reviewVo.getRes_id();
			for(int i=0; i < imageFileVoList.size(); i++) {
				ImageFileVO imageFileVo  = imageFileVoList.get(i);
				imageFileVo.setRev_no(rev_no);
				imageFileVo.setRes_no(res_no);
				result = fileService.insertReviewImgFile(imageFileVo);
				if(result)
					insertCount++;
			}
			if(imageFileVoList.size() == insertCount)
				result = true;
			else
				result = false;
		}
		return result;
	}
	
	/*
	 * 주간 맛집을 선택합니다.
	 */
	public List<UpsoVO> getWeeklyUpso() {
		List<UpsoVO> weeklyUpsoList = upsoService.showWeeklyUpso();
		return weeklyUpsoList;
	}
	
	/*
	 * 나가 픽한 업소 리스트를 가져옵니다.
	 */
	public List<UpsoVO> getMyUpsoList(SearchInfoVO searchInfoVo, HttpSession session) {
		String userId = getUserIdInSession(session);
		if(userId.equals(""))
			return null;
		searchInfoVo.setUser_id(userId);
		List<UpsoVO> myUpsoList = upsoService.showMyUpso(searchInfoVo);
		return myUpsoList;
	}

	public boolean upsoPickProcess(HttpSession session, int upso_id) {
		String user_id = getUserIdInSession(session);
		boolean is_pick = pickOrUnPick(user_id, upso_id);
		int count = 0;
		if(is_pick) {
			count = mainDao.deletePickData(upso_id, user_id);
		} else if(!is_pick) {
			count = mainDao.insertPickData(upso_id, user_id);
		}
		boolean result = (count == 0) ? false : true;
		return result;
	}

	private boolean pickOrUnPick(String user_id, int upso_id) {
		int pickCount = mainDao.isPick(upso_id, user_id);
		boolean result = (pickCount == 0) ? false : true;
		return result;
	}

	private String getUserIdInSession(HttpSession session) {
		String user_id = (String) session.getAttribute("SID");
		if(user_id == null)
			user_id = "";
		return user_id;
	}




}
