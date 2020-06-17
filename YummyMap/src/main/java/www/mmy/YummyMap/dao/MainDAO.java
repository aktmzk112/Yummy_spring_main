package www.mmy.YummyMap.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.MemberVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;

@Repository
public class MainDAO {

	private SqlSessionTemplate sqlSession;
	
	public MainDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int isShowUpSo(String upso_id) {
		return sqlSession.selectOne("mainSql.showUpSo", upso_id);
	}
	public SearchInfoVO isShowKeyword(String keyword) {
		return sqlSession.selectOne("mainSql.showKeyword", keyword);
	}

	public int insertUpSo(UpsoVO upSoVo) {
		return sqlSession.insert("mainSql.insertUpSo", upSoVo);
	}
	public int insertKeyword(SearchInfoVO searchInfoVo) {
		return sqlSession.insert("mainSql.insertKeyword", searchInfoVo);
	}
	
	public UpsoVO getUpSoDetailInfo(String upso_id) {
		return sqlSession.selectOne("mainSql.upSoDetailInfo", upso_id);
	}
	public List<UpsoVO> getUpSoList_keyword(SearchInfoVO searchInfoVo, PageUtil pageUtil){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchInfoVo", searchInfoVo);
		map.put("pageUtil", pageUtil);
		return sqlSession.selectList("mainSql.getUpSoList_keyword",map);
	}
	public List<UpsoVO> getUpSoList_groupByCategory(SearchInfoVO searchInfoVo){
		return sqlSession.selectList("mainSql.upsoListGroupByCategory",searchInfoVo);
	}
	public List<UpsoVO> getUpSoList_geolocation(SearchInfoVO searchInfoVo){
		return sqlSession.selectList("mainSql.upSoList_geolocation",searchInfoVo);
	}
	
}
