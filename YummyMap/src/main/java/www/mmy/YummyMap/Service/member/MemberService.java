package www.mmy.YummyMap.Service.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MemberDAO;
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
}
