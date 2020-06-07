package www.mmy.YummyMap.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.dao.MemberDAO;
import www.mmy.YummyMap.vo.MemberVO;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberDAO memberDao;
	
	@RequestMapping("/loginView.mmy")
	public String looginView() {
		String view = "member/Login"; 
		return view;
	}
	
	@RequestMapping("/loginProcess.mmy")
	public ModelAndView loginProcess(MemberVO memberVo, ModelAndView mv, RedirectView rv, HttpSession session) {
		if((String)session.getAttribute("SID") != null)
			rv.setUrl("/YummyMap/mainList.mmy");
		int resultCnt = memberDao.loginCheck(memberVo);
		if(resultCnt == 1) {
			rv.setUrl("/YummyMap/mainList.mmy");
		} else {
			rv.setUrl("/YummyMap/member/loginView.mmy");
		}
		mv.setView(rv);
		return mv;
	}
}
