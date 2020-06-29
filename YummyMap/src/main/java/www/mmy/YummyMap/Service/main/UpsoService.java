package www.mmy.YummyMap.Service.main;

import java.util.List;

import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.ImageFileVO;
import www.mmy.YummyMap.vo.RatingUpsoVO;
import www.mmy.YummyMap.vo.ReviewVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

public interface UpsoService {

	public UpsoVO getUpsoDetailInfo(int upso_id, String user_id);
	
	public boolean isShowUpso(int upso_id);

	public int insertUpso(UpsoVO upsoVo,  SearchInfoVO searchInfoVo);
	
	public List<UpsoVO> getUpsoList(SearchInfoVO searchInfoVo, PageUtil pageUtil);
	
	public RatingUpsoVO getRatingInfo(int upso_id);
	
	public List<ReviewVO> getReviewList(int upso_id);
	
	public List<ImageFileVO> getReviewImgList(int rev_no);
	
	public boolean insertReview(ReviewVO reviewVo);
	
	public List<UpsoVO> showWeeklyUpso();
	
	public List<UpsoVO> showMyUpso(SearchInfoVO searchInfoVo);

}
