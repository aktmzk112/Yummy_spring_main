package www.mmy.YummyMap.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import www.mmy.YummyMap.vo.admin.ChartCntVO;
import www.mmy.YummyMap.vo.admin.ResCntVO;

public class ChartDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	//카테고리 가져오기 전담 함수
	public List<ResCntVO> category() {
		return sqlSession.selectList("chartSQL.category");
	}
	
	public ChartCntVO maindata() {
		return sqlSession.selectOne("chartSQL.mainCnt");
	}
	
}
