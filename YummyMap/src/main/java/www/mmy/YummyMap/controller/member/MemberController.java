package www.mmy.YummyMap.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.Service.member.MemberService;
import www.mmy.YummyMap.util.ProjectUrl;
import www.mmy.YummyMap.vo.MemberVO;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/loginView.mmy")
	public String looginView() {
		String view = "member/Login"; 
		return view;
	}
	
	@RequestMapping("/loginProcess.mmy")
	public ModelAndView loginProcess(MemberVO memberVo, ModelAndView mv, RedirectView rv, HttpSession session) {
		int resultCnt = memberService.loginCheck(memberVo, session);
		if(resultCnt == 1) {
			rv.setUrl(ProjectUrl.MAIN_LIST_VIEW);
		} else {
			rv.setUrl(ProjectUrl.LOGIN_VIEW);
		}
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/logoutProcess.mmy")
	public ModelAndView logoutProcess(ModelAndView mv, RedirectView rv, HttpSession session) {
		memberService.userService.logoutProcess(session);
		rv.setUrl(ProjectUrl.LOGIN_VIEW);
		mv.setView(rv);
		return mv;
	}    
}
