package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class shopping_admin_Controller extends shopping_module{

	PrintWriter pw = null;
	
	@GetMapping("/admin")
	public String home() {
		return "index";
	}
	
	
	//로그아웃 파트
	@GetMapping("/logout.do")
	public String logout(@SessionAttribute(name = "list",required = false) String list,HttpServletRequest req,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		String result=log_out(req);
		if(result=="ok") {
			this.pw.print("<script>alert('로그아웃 되셨습니다.');location.href='./index.jsp';</script>");
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
			this.pw.print("<script>alert('잘못된 접근입니다.');location.href='./index.jsp';</script>");
			this.pw.close();
		}else {
			ArrayList<ArrayList<Object>> ar =this.admini();
			m.addAttribute("lists",ar);
		}
		return null;
	}
	
	//로그인 파트
	@PostMapping("/login.do")
	public void login_(String sid,String spass,shopping_admin_dao dao,HttpServletResponse res,HttpServletRequest req) throws Exception{
		try {
			res.setContentType("text/html;charset=utf-8");
			this.pw=res.getWriter();
			ArrayList<Object> callback=this.login(sid, spass,res,dao);		
			if(callback.get(7)==(Object)0) {
			this.pw.print("<script>alert('승인되지 않은 사용자입니다. 승인 이후 로그인 가능합니다.');history.back();</script>");	
			}else{
				HttpSession hs = req.getSession();
				hs.setMaxInactiveInterval(1800);
				hs.setAttribute("list", callback);
				System.out.println("168196");
				this.pw.print("<script>alert('로그인 되셨습니다!');location.href='./WEB-INF/veiws/shopping_admin.do';</script>");
				this.pw.close();
			}
		}catch(Exception e) {
			
		}finally {
			this.pw.close();
		}
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
