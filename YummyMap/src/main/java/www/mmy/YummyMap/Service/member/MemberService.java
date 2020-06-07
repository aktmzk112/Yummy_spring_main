package www.mmy.YummyMap.Service.member;

import javax.servlet.http.HttpSession;

import www.mmy.YummyMap.vo.MemberVO;

public interface MemberService {
	
	public boolean isUserLogin(HttpSession session);

	public int loginCheck(MemberVO memberVo, HttpSession session);
		
	public void logoutProcess(HttpSession session);
}
