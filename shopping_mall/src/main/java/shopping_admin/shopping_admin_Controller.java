package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class shopping_admin_Controller extends shopping_module{

	PrintWriter pw = null;
	

	
	//첫페이지 로그인 화면
	@GetMapping("/admin")
	public String home() {
		return "index";
	}
	
	//카테고리 등록
	@RequestMapping("/cate_write")
	public String cate_write(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}
		return "cate_write";
	}
	
	
	//신규상품 등록
	@RequestMapping("/product_write")
	public String product_write(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}
		return "product_write";
	}
	
	
	//관리자 페이지 로드
	@RequestMapping("/add_master")
    public String addMaster(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}
        return "add_master";
    }
	
	//쇼핑몰 기본설정 로드
	@RequestMapping("/siteinfo")
	public String shopping_settings(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}else {
			shopping_settings_dao dao= this.sp_set_sel();
		if(dao!=null) {
		m.addAttribute("settings_list",dao.list());
		}
		}
		return "siteinfo";
	}
	
	//쇼핑몰 회원관리 로드
	@RequestMapping("/shop_member_list")
	public String member_list(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}
		return "./shop_member_list";
	}
	
	
	//쇼핑몰 상품관리 로드
	@RequestMapping("/cate_list")
	public String cate_list(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m,shopping_cate_dao dao) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}else if(dao!=null){
		//이거 dao형태로 select list형태로 배열 받아야됨
		ArrayList<Object> arr =	dao.list();
		m.addAttribute("cate_list",arr);
		}
		return "cate_list";
	}
	
	//카테고리 생성 파트 
	@PostMapping("/cate_make")
	public void cate_make(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,shopping_cate_dao dao) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		if(list==null) {
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		}else {
			if(dao!=null) {
			int result = this.cate_make(dao);
				if(result==1) {
				this.pw.print("<script>alert('정상적으로 등록 되었습니다.');location.href='./cate_list';</script>");
				}
				else {
					this.pw.print("<script>alert('데이터 오류로 인해 등록에 실패하였습니다.');history.back();</script>");	
				}
			}
		}
		if(this.pw!=null) {
			this.pw.close();
		}
		
	}
	
	//쇼핑몰 기본설정
	@PostMapping("/siteinfo_write")
	public String siteinfo_write(@ModelAttribute shopping_settings_dao dao,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		try {
		if(dao!=null) {
		int result = this.sp_set(dao);
		if(result==1) {
			this.pw.print("<script>alert('정상적으로 저장 되었습니다.');location.href='siteinfo';</script>");
			}
		}
		}catch(Exception e) {
			System.out.println(e);
			this.pw.print("<script>alert('데이터 오류로 인하여 저장하지 못하였습니다. 잠시후 다시 시도해주세요!');history.back();</script>");
		}finally {
			if(this.pw!=null) {
				this.pw.close();
			}
		}
		return null;
	}
	//관리자 승인파트
	@PostMapping("/approval")
	public String approval(int master,int sidx,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
	 	int result = this.approval_module(master, sidx);
		if(result==1) {
			this.pw.print("<script>alert('정상적으로 변경 되었습니다.');location.href='./shopping_admin.do';</script>");
		}else {
			this.pw.print("<script>alert('변경중 오류 발생으로 실패하였습니다.');history.back();</script>");
		}this.pw.close();
		return null;
	}
	
	
	//로그아웃 파트
	@GetMapping("/logout.do")
	public String logout(@SessionAttribute(name = "list",required = false) String list,HttpServletRequest req,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		String result=log_out(req);
		if(result=="ok") {
			this.pw.print("<script>alert('로그아웃 되셨습니다.');location.href='./admin';</script>");
		}
		this.pw.close();
		return null;
	}
	
	//리스트 출력 파트
	@GetMapping("/shopping_admin.do")
	public String list_print(@SessionAttribute(name = "list",required = false) ArrayList<Object> arr,HttpServletResponse res,Model m) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		if(!arr.get(3).equals((Object)2)) {
			this.pw.print("<script>alert('잘못된 접근입니다.');location.href='./admin';</script>");
			this.pw.close();
		}else {
			List<shopping_admin_dao> ar =this.admini();
			m.addAttribute("lists",ar);
		}
		return "shopping_admin";
	}
	
	
	
	//로그인 파트
	@PostMapping("/shopping_admin.do")
	public String login_(String sid,String spass,shopping_admin_dao dao,HttpServletResponse res,HttpServletRequest req) throws Exception{
		try {
			res.setContentType("text/html;charset=utf-8");
			this.pw=res.getWriter();
			ArrayList<Object> callback=this.login(sid, spass,res,dao);	
			if(callback!=null) {
			if(callback.get(3)==(Object)0) {
			this.pw.print("<script>alert('승인되지 않은 사용자입니다. 승인 이후 로그인 가능합니다.');history.back();</script>");	
			}else{
			HttpSession hs = req.getSession();
			hs.setMaxInactiveInterval(1800);
			hs.setAttribute("list", callback);
				this.pw.print("<script>alert('로그인 되셨습니다!');location.href='./shopping_admin.do';</script>");
			}
			}else {
				this.pw.print("<script>alert('아이디와 비밀번호를 확인해주세요!');history.back();</script>");
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(this.pw!=null) {
			this.pw.close();
			}
		}
		return null;
	}
	
	//가입등록
	@PostMapping("/signup.do")
	public String sign_up(shopping_admin_dao dao,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		int callback=this.signup(dao);
		if(callback==1) {
			this.pw.print("<script>alert('가입등록이 완료되었습니다. 로그인은 관리자승인 이후 가능합니다.');location.href='./index.jsp';</script>");
		}else {
			this.pw.print("<script>alert('데이터 오류로 인하여 가입등록에 실패하였습니다.');history.back();</script>");
		}
		this.pw.close();
		return null;
	}
	
	//아이디 중복체크
	@GetMapping("/idcheck.do")
	public String idcheck(String sid,HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		try{
		 String callback = this.idck(sid);
		if(callback=="ok") {
			this.pw.print("ok");
		}
		else {
			this.pw.print("error");
		}
		}catch(Exception e) {
			
		}
		finally {
			this.pw.close();
		}
		return null;
	}
	
	
}
