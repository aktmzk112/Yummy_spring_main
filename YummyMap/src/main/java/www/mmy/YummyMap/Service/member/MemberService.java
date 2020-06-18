package www.mmy.YummyMap.Service.member;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MemberDAO;
import www.mmy.YummyMap.vo.JoinMailVO;
import www.mmy.YummyMap.vo.MemberVO;

@Service
public class MemberService {

	public UserService userService;
	MemberDAO memberDao;
	
	public MemberService(UserService userService, MemberDAO memberDao) {
		this.userService = userService;
		this.memberDao = memberDao;
	}
	
	public int loginCheck(MemberVO memberVo, HttpSession session) {
		if(userService.isUserLoggedin(session)) {
			return 1;
		} else {
			int resultCnt = memberDao.loginCheck(memberVo);
			if(resultCnt == 1) {
				userService.loginProcess(session, memberVo);
			}
			return resultCnt;
		}
	}
	
	public int jmailCnt(JoinMailVO jmailVo) {
		int cnt = memberDao.getjMailCnt(jmailVo);
		return cnt;
	}
	
	public void addjoinMail(JoinMailVO jmailVo) {
		memberDao.addjoinMail(jmailVo);
	}
	
	public void upjoinMail(JoinMailVO jmailVo) {
		memberDao.upjoinMail(jmailVo);
	}
	
	public int joinMaillOk(JoinMailVO jmailVo) {
		int cnt = memberDao.joinMailOk(jmailVo);
		return cnt;
	}
	
	public int idCk(MemberVO mvo) {
		int cnt = 0;
		cnt = memberDao.idCheck(mvo);
		return cnt;
	}
	
	public int addMember(MemberVO mvo) {
		int cnt = 0;
		cnt = memberDao.addMember(mvo);
		return cnt;
	}
	
	public int rmMail(HashMap<String, String> map) {
		int cnt = 0;
		cnt = memberDao.rmjoinMail(map);
		return cnt;
	}
	
}
