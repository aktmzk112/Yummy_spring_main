package www.mmy.YummyMap.Service.main;
/**
 * 업소 서비스를 구현한 클래스입니다.
 * 
 * @author	김종형
 * @see		www.mmy.YummyMap.Service.main.UpSoService
 */

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.vo.RatingUpsoVO;
import www.mmy.YummyMap.vo.ReviewVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;


@Service
public class UpsoService {
	private MainDAO mainDao;

	public UpsoService(MainDAO mainDao) {
		this.mainDao = mainDao;
	}
	public UpsoVO getUpSoDetailInfo(String upso_id) {
		UpsoVO upSoVo = mainDao.getUpSoDetailInfo(upso_id);
		return upSoVo;
	}

	public boolean isShowUpSo(String upso_id) {
		int resultCnt = mainDao.isShowUpSo(upso_id);
		boolean bool = resultCnt > 0 ? true : false;
		return bool;
	}

	public int insertUpSo(UpsoVO upSoVo) {
		int resultCnt = mainDao.insertUpSo(upSoVo);
		return resultCnt;
	}
	public int insertKeyword(SearchInfoVO searchInfoVo) {
		int resultCnt = mainDao.insertKeyword(searchInfoVo);
		return resultCnt;
	}
	
	public RatingUpsoVO getRatingInfo(String upso_id) {
		List<RatingUpsoVO> ratingList = mainDao.getUpsoRatingInfo(upso_id);
		RatingUpsoVO ratingUpsoVo = new RatingUpsoVO();
		for(int i=0; i<ratingList.size(); i++) {
			RatingUpsoVO tmp = ratingList.get(i);
			int rating_value = tmp.getRating_value();
			switch(rating_value) {
			case 1:
				ratingUpsoVo.setRating_star1(tmp.getRating_value_count());
				break;
			case 2:
				ratingUpsoVo.setRating_star2(tmp.getRating_value_count());
				break;
			case 3:
				ratingUpsoVo.setRating_star3(tmp.getRating_value_count());
				break;
			case 4:
				ratingUpsoVo.setRating_star4(tmp.getRating_value_count());
				break;
			case 5:
				ratingUpsoVo.setRating_star5(tmp.getRating_value_count());
				break;
			}
		}
		int total = ratingUpsoVo.getRating_total();
		double per1 = ratingUpsoVo.getRating_star1() / (double)total * 100.;
		double per2 = ratingUpsoVo.getRating_star2() / (double)total * 100.;	
		double per3 = ratingUpsoVo.getRating_star3() / (double)total * 100.;
		double per4 = ratingUpsoVo.getRating_star4() / (double)total * 100.;
		double per5 = ratingUpsoVo.getRating_star5() / (double)total * 100.;
		ratingUpsoVo.setStar1_per(per1);
		ratingUpsoVo.setStar2_per(per2);
		ratingUpsoVo.setStar3_per(per3);
		ratingUpsoVo.setStar4_per(per4);
		ratingUpsoVo.setStar5_per(per5);
		return ratingUpsoVo;
	}
	
	public List<ReviewVO> getReviewList(String upso_id){
		List<ReviewVO> reviewList = mainDao.getReviewList(upso_id);
		return reviewList;
	}
	
	public boolean insertReview(ReviewVO reviewVo) {
		boolean isSuccess = false;
		int result = mainDao.insertReview(reviewVo);
		if(result == 1) {
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}

}

