package www.mmy.YummyMap.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import www.mmy.YummyMap.vo.AdminVO;

public class AdminDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int loginck(AdminVO avo) {
		return sqlSession.selectOne("adminSQL.loginck",avo);
	}
	
	
}
