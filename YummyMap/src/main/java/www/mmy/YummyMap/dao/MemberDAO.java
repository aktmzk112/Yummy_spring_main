package www.mmy.YummyMap.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import www.mmy.YummyMap.vo.MemberVO;

@Repository
public class MemberDAO {
	
	SqlSessionTemplate sqlSession;
	
	public MemberDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int loginCheck(MemberVO memberVo) {
		return sqlSession.selectOne("memberSql.loginCheck", memberVo);
	}
	
	public MemberVO getUserInfo(String mid) {
		return sqlSession.selectOne("memberSql.getUserInfo", mid);
	}
		
}
