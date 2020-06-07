package www.mmy.YummyMap.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import www.mmy.YummyMap.vo.AdminVO;

public class AdminDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//관리자 로그인 전담 함수
	public int loginck(AdminVO avo) {
		return sqlSession.selectOne("adminSQL.loginck",avo);
	}
	
	//회원 카운트 전담 함수
	public int memberCnt(AdminVO avo) {
		return sqlSession.selectOne("adminSQL.membercnt" , avo);
	}
	
	//관리자 회원관리 리스트 전담 함수
	public List getMemberList(HashMap map) {
		return sqlSession.selectList("adminSQL.memberList", map);
	}
	
	//회원 정보 수정 페이지 전담 함수
	public AdminVO getMemberInfo(AdminVO avo) {
		return sqlSession.selectOne("adminSQL.memberEdit" , avo);
	}
	
	//회정 정보 수정 전담 함수
	public int memberEdit(AdminVO avo) {
		return sqlSession.update("adminSQL.memberUpdate",avo);
	}
	
}
