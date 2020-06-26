package www.mmy.YummyMap.Service.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.mmy.YummyMap.Service.api.KaKaoMapRestApiService;
import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.ImageFileVO;
import www.mmy.YummyMap.vo.RatingUpsoVO;
import www.mmy.YummyMap.vo.ReviewVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Service
public class ParsingUpsoService implements UpsoService {

	private UpsoService simpleUpsoService;
	private KaKaoMapRestApiService kakaoMapService;
	@Autowired
	private KeywordService keywordService; 
	
	
	public ParsingUpsoService(SimpleUpsoService simpleUpsoService, KaKaoMapRestApiService kakaoMapService) {
		this.simpleUpsoService = simpleUpsoService;
		this.kakaoMapService = kakaoMapService;
	}
	
	@Override
	public UpsoVO getUpsoDetailInfo(int upso_id) {
		return simpleUpsoService.getUpsoDetailInfo(upso_id);
	}
	@Override
	public boolean isShowUpso(int upso_id) {
		return simpleUpsoService.isShowUpso(upso_id);
	}
	
	/*
	 * 카카오맵 API파싱 결과를 DB에 저장합니다.
	 * return : 실제 데이터베이스에 저장된 데이터 수이다.
	 */
	@Override
	public int insertUpso(UpsoVO upsoVo, SearchInfoVO searchInfoVo) {
		if(upsoVo != null) {
			return simpleUpsoService.insertUpso(upsoVo, searchInfoVo);
		}
		int totalCount = 0;
		List<UpsoVO> upsoList = kakaoMapService.getUpsoList(searchInfoVo);
		for(int i=0; i < upsoList.size(); i++) {
			UpsoVO upso = upsoList.get(i);
			int upso_id = upso.getId();
			if(isShowUpso(upso_id) == false) {
				// 업소가 데이터베이스에 저장되어있지 않다면 저장합니다.
				totalCount += simpleUpsoService.insertUpso(upso, searchInfoVo);
			}
			// 키워드 또한 저장합니다.
			searchInfoVo.setUpso_id(upso_id);
			keywordService.insertKeyword(searchInfoVo);
		}
		searchInfoVo.setUpsoCount(upsoList.size());
		return totalCount;
	}
	
	@Override
	public List<UpsoVO> getUpsoList(SearchInfoVO searchInfoVo, PageUtil pageUtil) {
		return simpleUpsoService.getUpsoList(searchInfoVo, pageUtil);
	}
	
	@Override
	public RatingUpsoVO getRatingInfo(int upso_id) {
		return simpleUpsoService.getRatingInfo(upso_id);
	}
	@Override
	public List<ReviewVO> getReviewList(int upso_id) {
		return simpleUpsoService.getReviewList(upso_id);
	}
	@Override
	public List<ImageFileVO> getReviewImgList(int rev_no) {
		return simpleUpsoService.getReviewImgList(rev_no);
	}
	@Override
	public boolean insertReview(ReviewVO reviewVo) {
		return simpleUpsoService.insertReview(reviewVo);
	}
	
	
}
