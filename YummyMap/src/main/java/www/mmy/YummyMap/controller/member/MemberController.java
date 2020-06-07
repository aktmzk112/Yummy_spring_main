package www.mmy.YummyMap.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.Service.member.MemberService;
import www.mmy.YummyMap.vo.MemberVO;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/loginView.mmy")
	public String looginView() {
		String view = "member/Login"; 
		return view;
	}
	
	@RequestMapping("/loginProcess.mmy")
	public ModelAndView loginProcess(MemberVO memberVo, ModelAndView mv, RedirectView rv, HttpSession session) {
		if(memberService.isUserLogin(session)) {
			rv.setUrl("/YummyMap/main.mmy");
		} else {
			int resultCnt = memberService.loginCheck(memberVo, session);
			if(resultCnt == 1) {
				rv.setUrl("/YummyMap/main.mmy");
			} else {
				rv.setUrl("/YummyMap/member/loginView.mmy");
			}
		}
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/logoutProcess.mmy")
	public ModelAndView logoutProcess(ModelAndView mv, RedirectView rv, HttpSession session) {
		memberService.logoutProcess(session);
		rv.setUrl("/YummyMap/main.mmy");
		mv.setView(rv);
		return mv;
	}
}
