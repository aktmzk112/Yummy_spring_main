package www.mmy.YummyMap.Service.member;

import javax.servlet.http.HttpSession;

import www.mmy.YummyMap.vo.MemberVO;

public interface UserService {
	
	public boolean isUserLoggedin(HttpSession session);
		
	public void logoutProcess(HttpSession session);

	public void loginProcess(HttpSession session, MemberVO memberVo);
	
	public MemberVO getUserInfo(HttpSession session);
	
	
}
