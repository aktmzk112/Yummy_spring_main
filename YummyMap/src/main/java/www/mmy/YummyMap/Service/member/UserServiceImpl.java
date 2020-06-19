package www.mmy.YummyMap.Service.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MemberDAO;
import www.mmy.YummyMap.vo.MemberVO;

@Service
public class UserServiceImpl implements UserService {
	
	private MemberDAO memberDao;
	private KakaoAPI kakaoApi;
	public UserServiceImpl(MemberDAO memberDao, KakaoAPI kakaoApi) {
		this.memberDao = memberDao;
		this.kakaoApi = kakaoApi;
	}
	@Override
	public boolean isUserLoggedin(HttpSession session) {
		String userId = (String)session.getAttribute("SID");
		boolean bool = userId == null ? false : true;
		return bool;
	}
	@Override
	public void logoutProcess(HttpSession session) {
		String token = (String) session.getAttribute("Token");
		System.out.println(token + " 토 큰 ###################################");
		if(token == null || token.length() == 0) {
			session.removeAttribute("SID");
		}else {
			System.out.println("카카오 로그아웃 처리 ");
			kakaoApi.logoutone();
			kakaoApi.kakaoLogout(token);
			session.removeAttribute("Token");
			session.removeAttribute("SID");
		}
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
