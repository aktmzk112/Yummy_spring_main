package www.mmy.YummyMap.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import www.mmy.YummyMap.vo.MemberVO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpSoVO;

@Repository
public class MainDAO {

	private SqlSessionTemplate sqlSession;
	
	public MainDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int isShowUpSo(String upso_id) {
		return sqlSession.selectOne("mainSql.showUpSo", upso_id);
	}

	public int insertUpSo(UpSoVO upSoVo) {
		return sqlSession.insert("mainSql.insertUpSo", upSoVo);
	}
	
	public UpSoVO getUpSoDetailInfo(String upso_id) {
		return sqlSession.selectOne("mainSql.upSoDetailInfo", upso_id);
	}
	public List<UpSoVO> getUpSoListWithChart(SearchInfoVO searchInfoVo){
		return sqlSession.selectList("mainSql.upSoListWithChart",searchInfoVo);
	}
}
