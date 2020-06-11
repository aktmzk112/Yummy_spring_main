package www.mmy.YummyMap.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import www.mmy.YummyMap.dao.AdminDAO;
import www.mmy.YummyMap.util.PageUtil;
import www.mmy.YummyMap.vo.admin.AdminBoardVO;
import www.mmy.YummyMap.vo.admin.AdminCntVO;
import www.mmy.YummyMap.vo.admin.AdminVO;

@Controller
@RequestMapping("/admin")
public class Admin {
	@Autowired
	AdminDAO adminDao;
	
	//관리자 로그인 뷰 전담 함수
	@RequestMapping("/login.mmy")
	public String loginView() {
		return "admin/adminLogin";
	}
	
	
	//관리자 로그인 전담 함수
	@RequestMapping("/loginProc.mmy")
	public ModelAndView loginck(AdminVO avo , ModelAndView mv , HttpSession session) {
		int cnt = adminDao.loginck(avo);
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
	
	//관리자 메인 페이지 
	@RequestMapping("/main.mmy")
	public ModelAndView mainView(ModelAndView mv , HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		String view = "admin/main";
		if (sid == null || sid.length() == 0) {
			view = "/YummyMap/admin/login.mmy";
			RedirectView rv = new RedirectView(view);
			mv.setView(rv);
			return mv;
		}
		
		AdminCntVO acvo = adminDao.maindata();
		
		mv.addObject("CNT", acvo);
		mv.setViewName(view);		
		return mv;
	}
	//회원 관리 페이지 화면 *(회원관리 페이지) 뷰 요청
	@RequestMapping("/member.mmy")
	public ModelAndView memberView(ModelAndView mv , AdminVO avo ,PageUtil page ,String opts ,HttpSession session) {
		String view = "admin/member";
		if(session.getAttribute("SID") == null) {
			view = "/YummyMap/admin/login.mmy";
			RedirectView rv = new RedirectView(view);
			mv.setView(rv);
			return mv;
		}
			
		
		page.setNowPage(page.getNowPage());
		if(opts == null) {
		}
		else if(opts.equals("idch")) {
			mv.addObject("SCH" , avo.getMid());
			mv.addObject("OPT",opts);
			page.setNowPage(1);
		}else if(opts.equals("namech")) {
			mv.addObject("SCH" , avo.getMname());
			mv.addObject("OPT",opts);
			page.setNowPage(1);
		}
		int cnt = adminDao.memberCnt(avo);
		page.setTotalCount(cnt);
		page.setPageRow(5);
		page.setPageGroup(3);
		page.totalfun();
		
		
		HashMap hmap = new HashMap();
		hmap.put("avo" , avo);
		hmap.put("page" , page);
		
		ArrayList<AdminVO> list =  (ArrayList<AdminVO>) adminDao.getMemberList(hmap);
		for(int i=0; i<list.size(); i++) {
			list.get(i).setIssue();
		}
		
		mv.addObject("LIST" , list);
		mv.addObject("PAGE" , page);
		mv.setViewName(view);
		
		return mv;
	}
	
	//회원정보 수정 페이지
	@RequestMapping("/memberEdit.mmy")
	public ModelAndView memberEdit(AdminVO avo , ModelAndView mv) {
		String view = "admin/remember";
		
		avo = adminDao.getMemberInfo(avo);
		avo.setIssue();
		mv.addObject("MVO", avo);
		mv.setViewName(view);
		return mv;
	}
	
	//회원 정보 수정 전담 함수
	@RequestMapping("/memberEditProc.mmy")
	public ModelAndView memberEditProc(AdminVO avo , int nowPage , ModelAndView mv) {
		String view = "";
		avo.setMemail(avo.getEmail() +"@"+avo.getDomain());
		
		if(avo.getIssue() == null) {
			avo.setIssue("N");
		}else if(avo.getIssue().equals("ok")) {
			avo.setIssue("X");
		}
		int cnt = adminDao.memberEdit(avo);
		
		if(cnt == 1) {
			view = "/YummyMap/admin/member.mmy?nowPage="+nowPage;
		}
		
		RedirectView rv = new RedirectView(view);
		mv.setView(rv);
		return mv;
	}
	
	//회원 삭제 전담 함수
	@RequestMapping("memberDelProc.mmy")
	public ModelAndView memberDelProc(AdminVO avo , ModelAndView mv) {
		String view = "/YummyMap/admin/member.mmy";
		
		int cnt = adminDao.memberDel(avo);
		if(cnt == 0) {
			System.out.println("정상삭제 실패");
		}
		RedirectView rv = new RedirectView(view);
		mv.setView(rv);
		
		return mv;
	}
	
	//관리자 정보 수정 페이지 요청
	@RequestMapping("/adminEdit.mmy")
	public ModelAndView adminEdit(ModelAndView mv , HttpSession session , AdminVO avo) {
		
		avo.setMid((String) session.getAttribute("SID"));
		avo = adminDao.getMemberInfo(avo);
		
		String view = "admin/adminreview";
		
		mv.addObject("MVO" ,avo);
		mv.setViewName(view);
		
		return mv;
	}
	
	//관리자 정보 수정 전담 함수
	@RequestMapping("/adminEditProc.mmy")
	public ModelAndView adminEditProc(HttpSession session , AdminVO avo , ModelAndView mv) {
		String view = "/YummyMap/admin/member.mmy";
		
		avo.setMid((String) session.getAttribute("SID")); 
		
		avo.setMemail(avo.getEmail() + '@' + avo.getDomain());
			
		int cnt = adminDao.memberEdit(avo);
		
		RedirectView rv = new RedirectView(view);
		mv.setView(rv);
		
		return mv;
	}
	
	//게시글 관리 페이지 
	@RequestMapping("/boardList.mmy")
	public ModelAndView boardList(String opts ,AdminBoardVO abvo , ModelAndView mv , HttpSession session , PageUtil page) {
		
		String view = "admin/board";
		if(session.getAttribute("SID") == null) {
			view = "/YummyMap/admin/login.mmy";
			RedirectView rv = new RedirectView(view);
			mv.setView(rv);
			return mv;
		}
		

		
		page.setTotalCount(adminDao.boardCnt(abvo));
		page.setPageRow(2);
		page.setPageGroup(3);
		page.totalfun();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("abvo", abvo);
		map.put("page", page);
		
		ArrayList<AdminBoardVO> list = (ArrayList<AdminBoardVO>) adminDao.boardList(map);

		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		try {
			if(opts.equals("idch")) {
				mv.addObject("SCH" , abvo.getMid());
				mv.addObject("OPT" , opts);
			}else if(opts.equals("titlch")){
				mv.addObject("SCH" , abvo.getTitle());
				mv.addObject("OPT" , opts);
			}
		} catch (Exception e) {	}
		
		mv.setViewName(view);
		return mv;
	}
	
	//게시글 상세보기 요청
	@RequestMapping("/boardDetail.mmy")
	public ModelAndView boardDetail(AdminBoardVO advo, ModelAndView mv) {
		
		String view = "admin/boardDetail";
		advo = adminDao.detailBoard(advo);
		
		
		mv.addObject("BVO",advo);
		mv.setViewName(view);
		return mv;
		
	}
	
	//게시글 삭제 요청 
	@RequestMapping("/boardDel.mmy")
	public ModelAndView boardDel(int[] txtno , ModelAndView mv) {
		String view = "/YummyMap/admin/boardList.mmy";
		
		RedirectView rv = new RedirectView(view);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<txtno.length; i++) {
			list.add(txtno[i]);
		}

		int cnt = adminDao.boardDel(list);
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping("/shopadd.mmy")
	public String shopadd() {
		
		return "admin/shopadd";
	}
	
}
