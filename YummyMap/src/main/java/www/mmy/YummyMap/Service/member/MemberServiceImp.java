package www.mmy.YummyMap.Service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MemberDAO;
import www.mmy.YummyMap.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberDAO memberDao;
	@Override
	public boolean isUserLogin(HttpSession session) {
		String userId = (String)session.getAttribute("SID");
		boolean bool = userId == null ? false : true;
		return bool;
	}
	
	@Override
	public int loginCheck(MemberVO memberVo, HttpSession session) {
		int resultCnt = memberDao.loginCheck(memberVo);
		if(resultCnt == 1) {
			session.setAttribute("SID", memberVo.getMid());
		}
		return resultCnt;
	}

	@Override
	public void logoutProcess(HttpSession session) {
		session.removeAttribute("SID");
	}



}
