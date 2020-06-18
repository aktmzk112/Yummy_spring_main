package www.mmy.YummyMap.controller.member;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.Service.member.MemberService;
import www.mmy.YummyMap.Service.rsa.RsaServiceImpl;
import www.mmy.YummyMap.util.MailUtil;
import www.mmy.YummyMap.util.ProjectUrl;
import www.mmy.YummyMap.vo.JoinMailVO;
import www.mmy.YummyMap.vo.MemberVO;

@RequestMapping("/member")
@Controller
public class MemberController {
	@Autowired
	MailUtil yummyMail;

	MemberService memberService;

	public MemberController(RsaServiceImpl rsaServiceImpl, MemberService memberService) {
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
		if (resultCnt == 1) {
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
	public ModelAndView joinview(ModelAndView mv) {

		mv.setViewName("member/Register");

		return mv;
	}

	@RequestMapping("/mailCk.mmy")
	@ResponseBody
	public HashMap<String, String> mailCk(String email, HttpSession session) {
		String from = email;

		// 인증번호 생성
		Random ran = new Random();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			tmp.append(ran.nextInt(10));
		}

		String inck = tmp.toString();
		String title = "회원가입 인증 메일 입니다";
		StringBuffer mtxt = new StringBuffer();
		mtxt.append("<h2>반갑습니다. <b>YummyMap</b> 입니다.</h2> ");
		mtxt.append("<br> ");
		mtxt.append("<h3>요청 주신 인증 번호는 <b style=\"color: blue;\">" + inck + "</b> 입니다.</h3> ");
		mtxt.append("<br> ");
		mtxt.append("<h3>인증 번호 입력 란에 입력해주시고 인증 확인후 진행 부탁드립니다</h3> ");
		mtxt.append("<h3>감사합니다.</h3> ");

		JoinMailVO jmail = new JoinMailVO();

		jmail.setMail(from);
		jmail.setCftnum(inck);
		int cnt = memberService.jmailCnt(jmail);

		try {
			if (cnt == 1) {
				memberService.upjoinMail(jmail);
			} else if (cnt == 0) {
				memberService.addjoinMail(jmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		yummyMail.getSend(from, title, mtxt.toString());
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("emailCk", "ok");
		return map;
	}
	
	@RequestMapping("/mailOk.mmy")
	@ResponseBody
	public HashMap<String, String> joinMailOk(JoinMailVO jmvo){
		int cnt = memberService.joinMaillOk(jmvo);
		System.out.println(cnt + " cnt ###############################");
		HashMap<String, String> map = new HashMap<String, String>();
		if(cnt == 1) {
			map.put("status", "ok");
		}else {
			map.put("status", "no");
		}
		
		return map;
	}
	
	//아이디 체크 비동기 통신
	@RequestMapping("/idCheck.mmy")
	@ResponseBody
	public HashMap<String, String> idCheck(MemberVO mvo) {
		System.out.println("#################################");
		HashMap<String , String> map = new HashMap<String, String>();
		int cnt = memberService.idCk(mvo);
		System.out.println(cnt + " cnt");
		if(cnt == 0 ) {
			map.put("result" , "ok");
		}else {
			map.put("result" , "no");
		}
		return map;
	}
	
	//회원가입처리 
	@RequestMapping("/joinProcess.mmy")
	public ModelAndView joinProc(MemberVO mvo , ModelAndView mv) {
		int cnt = memberService.addMember(mvo);
		String view = "";
		if(cnt == 1) {
			view = "/YummyMap/member/loginView.mmy";
			System.out.println("정상 가입 완료");
			
		}else {
			view ="/YummyMap/member/join.mmy";
			System.out.println("가입 실패!!!");
		}

		RedirectView rv = new RedirectView(view);
		mv.setView(rv);
		
		return mv;
	}
}
