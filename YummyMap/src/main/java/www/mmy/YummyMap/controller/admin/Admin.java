package www.mmy.YummyMap.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.dao.AdminDAO;
import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.AdminVO;

@Controller
@RequestMapping("/admin")
public class Admin {
	@Autowired
	AdminDAO adminDao;
	
	//관리자 로그인 뷰 전담 함수
	@RequestMapping("/login.mmy")
	public String loginView() {
		System.out.println("#######");
		return "admin/adminLogin";
	}
	
	
	//관리자 로그인 전담 함수
	@RequestMapping("/loginProc.mmy")
	public ModelAndView loginck(AdminVO avo , ModelAndView mv , HttpSession session) {
		int cnt = adminDao.loginck(avo);
		System.out.println(cnt + " cnt ##################################");
		RedirectView rv =null;
		if(cnt == 1) {
			 session.setAttribute("SID", avo.getMid());
			 rv = new RedirectView("/YummyMap/admin/main.mmy");
		}else {
			 rv = new RedirectView("/YummyMap/admin/login.mmy?noad=b");
		}
		mv.setView(rv);
		return mv;
	}
	//메인 화면 *(회원관리 페이지) 뷰 요청
	@RequestMapping("/main.mmy")
	public ModelAndView mainView(ModelAndView mv , AdminVO avo ,PageUtil page) {
		String view = "admin/main";
		
		mv.setViewName(view);
		
		return mv;
	}
	
	
	
}
