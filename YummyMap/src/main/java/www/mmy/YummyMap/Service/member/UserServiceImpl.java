package www.mmy.YummyMap.Service.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MemberDAO;
import www.mmy.YummyMap.vo.MemberVO;

@Service
public class UserServiceImpl implements UserService {
	
	MemberDAO memberDao;
	
	public UserServiceImpl(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}
	@Override
	public boolean isUserLoggedin(HttpSession session) {
		String userId = (String)session.getAttribute("SID");
		boolean bool = userId == null ? false : true;
		return bool;
	}
	@Override
	public void logoutProcess(HttpSession session) {
		session.removeAttribute("SID");
	}
	@Override
	public void loginProcess(HttpSession session, MemberVO memberVo) {
		session.setAttribute("SID", memberVo.getMid());
	}
	@Override
	public MemberVO getUserInfo(HttpSession session) {
		String userId = (String) session.getAttribute("SID");
		if(userId == null) {
			return null;
		} else {
			return memberDao.getUserInfo(userId);
		}
	}

}
