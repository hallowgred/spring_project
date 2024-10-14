package shopping;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shopping_admin.shopping_module;


//Controller part
@Controller
public class shopping_Controller {

	PrintWriter pw=null;
	
	@Resource(name =  "shopping_module2")
	private shopping_Service ss;
	
	@Resource(name =  "shopping_module")
	private shopping_module sm;
	
	@PostMapping("/loginok")
	public void loginok(@RequestParam(required = false) String saveid, loginEntity ett, HttpServletResponse res) throws Exception {
	    res.setContentType("text/html;charset=utf-8");
	    join_DTO result = ss.login(ett); // 타입 변경
	    this.pw = res.getWriter();
	    if (result != null) {
	    	if(result.getMstat().equals("정상")) {
	        this.pw.print("<script>alert('로그인 되셨습니다.');</script>");
	    	}else {
	    		this.pw.print("<script>alert('해당 고객님은 현재 계정이 정지된 상황 입니다. 고객센터에 문의하세요');</script>");
	    	}
	    } else {
	        this.pw.print("<script>alert('로그인 실패.');</script>");
	    }
	    this.pw.close();
	}


	
	@GetMapping("/login")
	public String login() {
		return null;
	}
	
	@PostMapping("/joinok")
	public String joinok(@RequestParam String emailuse1,@RequestParam String smsuse1, join_DTO dto,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(emailuse1.equals("on")) {
			dto.setEmailuse("Y");
		}else {
			dto.setEmailuse("N");
		}
		if(smsuse1.equals("on")) {
			dto.setSmsuse("Y");
		}else {
			dto.setSmsuse("N");
		}
		int result = ss.joinMember(dto);
		this.pw=res.getWriter();
		if(result==1) {
			this.pw.print("<script>alert('정상적으로 회원가입 되셨습니다.');location.href='./login';</script>");
		}else {
			this.pw.print("<script>alert('회원가입에 실패하였습니다. 잠시 후 다시 시도해주세요.');history.back();</script>");
		}
		this.pw.close();
		return null;
	}
	
	
	@PostMapping("/email_cc")
	@ResponseBody
	public String email_cc(@RequestBody String email) {
		JSONArray jsonArray = new JSONArray(email);
		String extractedEmail = jsonArray.getString(0); 
		String extractedCode = jsonArray.getString(1);
		System.out.println(extractedEmail + ", " + extractedCode);
		String result = new authemail_post(extractedEmail, extractedCode).post_execute();
		return result;
	}
	
	
	
	@PostMapping("/join_idcheck")
	public void join_idcheck(@RequestBody String mid,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		String callback = ss.idck_join(mid);
		this.pw.print(callback);
		this.pw.close();
	}
	
	@RequestMapping("/agree")
	public String agree() throws Exception{
		return null;
	}
	
	@RequestMapping("/join")
	public String join() {
		return null;
	}
}
