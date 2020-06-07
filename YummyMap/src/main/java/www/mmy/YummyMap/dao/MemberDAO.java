package www.mmy.YummyMap.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import www.mmy.YummyMap.vo.MemberVO;

@Component
public class MemberDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int loginCheck(MemberVO memberVo) {
		return sqlSession.selectOne("memberSql.loginCheck", memberVo);
	}
		
}
