package www.mmy.YummyMap.controller.member;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.Service.member.MemberService;
import www.mmy.YummyMap.util.MailUtil;
import www.mmy.YummyMap.util.ProjectUrl;
import www.mmy.YummyMap.vo.MemberVO;

@RequestMapping("/member")
@Controller
public class MemberController {
	@Autowired
	MailUtil yummyMail;
	
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
			rv.setUrl(ProjectUrl.MAIN_LIST_VIEW.getUrl());
		} else {
			rv.setUrl(ProjectUrl.LOGIN_VIEW.getUrl());
		}
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/logoutProcess.mmy")
	public ModelAndView logoutProcess(ModelAndView mv, RedirectView rv, HttpSession session) {
		memberService.userService.logoutProcess(session);
		rv.setUrl(ProjectUrl.LOGIN_VIEW.getUrl());
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/join.mmy")
	public String joinview() {
		
		return "member/Register";
	}
	
	@RequestMapping("/mailCk.mmy")
	
	public @ResponseBody String mailCk(String email) {
		
		String from = email;
		
		System.out.println(from + " 발송지 ############################################");
		//인증번호 생성
		Random ran = new Random();
		StringBuffer tmp = new StringBuffer();
		for(int i=0; i<6; i++) {
			tmp.append(ran.nextInt(10));
		}
		
		String inck = tmp.toString();
		String title = "회원가입 인증 메일 입니다";
		StringBuffer mtxt = new StringBuffer();
		mtxt.append("<h2>반갑습니다. <b>YummyMap</b> 입니다.</h2> ");
		mtxt.append("<br> ");
		mtxt.append("<h3>요청 주신 인증 번호는 <b style=\"color: blue;\">"+inck+"</b> 입니다.</h3> ");
		mtxt.append("<br> ");
		mtxt.append("<h3>인증 번호 입력 란에 입력해주시고 인증 확인후 진행 부탁드립니다</h3> ");
		mtxt.append("<h3>감사합니다.</h3> ");
		
		yummyMail.getSend(from, title, mtxt.toString());
		
		String emailCk = inck;
		
		return emailCk;
	}
}
