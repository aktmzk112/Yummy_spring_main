package www.mmy.YummyMap.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import www.mmy.YummyMap.vo.JoinMailVO;
import www.mmy.YummyMap.vo.MemberVO;

@Repository
public class MemberDAO {
	
	private SqlSessionTemplate sqlSession;
	
	public MemberDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int loginCheck(MemberVO memberVo) {
		return sqlSession.selectOne("memberSql.loginCheck", memberVo);
	}
	
	public MemberVO getUserInfo(String mid) {
		return sqlSession.selectOne("memberSql.getUserInfo", mid);
	}
	
	public int getjMailCnt(JoinMailVO jmailVo) {
		return sqlSession.selectOne("memberSql.joinMailSh", jmailVo);
	}
	
	public int addjoinMail(JoinMailVO jmailVo) {
		return sqlSession.insert("memberSql.addjoinMail", jmailVo);
	}
	
	public int upjoinMail(JoinMailVO jmailVo) {
		return sqlSession.update("memberSql.upjoinMail", jmailVo);
	}
	
	public int joinMailOk(JoinMailVO jmailVo) {
		return sqlSession.selectOne("memberSql.joinMailOk" , jmailVo);
	}
	
	public int idCheck(MemberVO mvo) {
		return sqlSession.selectOne("memberSql.idCheck", mvo);
	}
	
	public int addMember(MemberVO mvo) {
		return sqlSession.insert("memberSql.addMember",mvo);
	}
	
	public int rmjoinMail(HashMap<String, String> map) {
		return sqlSession.insert("memberSql.rmjoinMail", map);
	}
		
}
