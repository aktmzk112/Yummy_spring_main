package www.mmy.YummyMap.dao;
/**
 * 메인페이지에서 필요한 DAO 작업을 전담하는 클래스입니다.
 * 
 * @author	김종형
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.MemberVO;
import www.mmy.YummyMap.vo.RatingUpsoVO;
import www.mmy.YummyMap.vo.ReviewVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Repository
public class MainDAO {

	private SqlSessionTemplate sqlSession;
	
	public MainDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/*
	 * 업소가 DB 테이블에 저장되어있는지 조회합니다
	 */
	public int isShowUpSo(String upso_id) {
		return sqlSession.selectOne("mainSql.showUpSo", upso_id);
	}
	/*
	 * 입력된 키워드의 분석된 정보를 조회합니다.
	 * 조회 결과
	 * 		키워드 분석 정보, 해당 키워드로 조회된 업소 총 수
	 */
	public SearchInfoVO isShowKeyword(String keyword) {
		return sqlSession.selectOne("mainSql.showKeyword", keyword);
	}
	/*
	 * 업소의 정보를 DB 테이블에 저장합니다.
	 */
	public int insertUpSo(UpsoVO upSoVo) {
		return sqlSession.insert("mainSql.insertUpSo", upSoVo);
	}
	/*
	 * 분석된 키워드정보를 DB 테이블에 저장합니다.
	 */
	public int insertKeyword(SearchInfoVO searchInfoVo) {
		return sqlSession.insert("mainSql.insertKeyword", searchInfoVo);
	}
	
	/*
	 * 업소의 디테일한 정보를 조회합니다.
	 */
	public UpsoVO getUpSoDetailInfo(String upso_id) {
		return sqlSession.selectOne("mainSql.upSoDetailInfo", upso_id);
	}
	/*
	 * 키워드에 해당하는 업소 리스트를 출력합니다.
	 * 	SearchInfoVO 에 담긴 정보에 의해서 동적인 질의명령이 완성됩니다.
	 * 		SearchInfoVO에는 keyword가 필수로 담겨있어야 합니다.
	 * 						나머지는 옵션이 될 수 있습니다.
	 * 						자세한 정보는 SearchInfoVO를 참조하세요.
	 * param :	SearchInfoVO - 검색정보가 담겨있는 vo
	 * 			PageUtil	 - 페이징 처리 정보가 담긴 util
	 */
	public List<UpsoVO> getUpSoList_keyword(SearchInfoVO searchInfoVo, PageUtil pageUtil){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchInfoVo", searchInfoVo);
		map.put("pageUtil", pageUtil);
		return sqlSession.selectList("mainSql.getUpSoList_keyword",map);
	}
	/*
	 * 카테고리로 그룹핑된 업소의 총 수를 조회합니다.
	 */
	public int countUpso_category(SearchInfoVO searchInfoVo) {
		return sqlSession.selectOne("mainSql.countUpso_category", searchInfoVo);
	}
	/*
	 * 사용자의 위치를 기반으로 업소 리스트를 출력합니다.
	 * param : SearchInfoVO
	 * 위치 기반 출력을 원할시 SearchInfoVO에 유저의 x, y 값이 필수로 담겨있어야 합니다.
	 */
	public List<UpsoVO> getUpSoList_geolocation(SearchInfoVO searchInfoVo){
		return sqlSession.selectList("mainSql.upSoList_geolocation",searchInfoVo);
	}
	/*
	 * 업소의 리뷰 평점 정보를 조회합니다.
	 * param : upso_id (업소 id)
	 */
	public List<RatingUpsoVO> getUpsoRatingInfo(String upso_id) {
		return sqlSession.selectList("chartSQL.upso_rating_total", upso_id);
	}
	/*
	 * 업소정보에 등록된 모든 리뷰를 조회합니다.
	 *  param : upso_id (업소 id)
	 */
	public List<ReviewVO> getReviewList(String upso_id) {
		return sqlSession.selectList("mainSql.reviewList", upso_id);
	}
	/*
	 * 리뷰정보를 DB에 저장합니다.
	 */
	public int insertReview(ReviewVO reviewVo) {
		return sqlSession.insert("mainSql.insertReview", reviewVo);
	}
}
