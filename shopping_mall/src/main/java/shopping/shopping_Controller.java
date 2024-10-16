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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	        this.pw.print("<script>alert('로그인 되셨습니다.');location.href='./main'</script>");
	    	}else {
	    		this.pw.print("<script>alert('해당 고객님은 현재 계정이 정지된 상황 입니다. 고객센터에 문의하세요');history.back();</script>");
	    	}
	    } else {
	        this.pw.print("<script>alert('아이디와 비밀번호를 확인해주세요.');history.back();</script>");
	    }
	    this.pw.close();
	}

	@GetMapping("/main")
	public String main(Model m) {
		m.addAttribute("getCategory",ss.getCategory());
		m.addAttribute("getFooter",ss.getFooter());
		m.addAttribute("getProduct",ss.getproduct());
		return null;
	}

	
	@GetMapping("/login")
	public String login() {
		return null;
	}
	
	@PostMapping("/joinok")
	public String joinok(@RequestParam(required = false) String emailuse1,
	                     @RequestParam(required = false) String smsuse1, 
	                     join_DTO dto, 
	                     HttpServletResponse res) throws Exception {
	    
	    res.setContentType("text/html;charset=utf-8");

	    // 필수 입력값 유효성 검사
	    if (dto.getMid() == null || dto.getMid().isEmpty()) {
	        this.pw = res.getWriter();
	        this.pw.print("<script>alert('아이디를 입력해 주세요.');history.back();</script>");
	        this.pw.close();
	        return null;
	    }

	    if (dto.getMpass() == null || dto.getMpass().isEmpty()) {
	        this.pw = res.getWriter();
	        this.pw.print("<script>alert('비밀번호를 입력해 주세요.');history.back();</script>");
	        this.pw.close();
	        return null;
	    }

	    // 이메일 수신 동의 처리
	    dto.setEmailuse("on".equals(emailuse1) ? "Y" : "N");

	    // SMS 수신 동의 처리
	    dto.setSmsuse("on".equals(smsuse1) ? "Y" : "N");

	    try {
	        // 회원가입 처리
	        int result = ss.joinMember(dto);
	        this.pw = res.getWriter();

	        // 결과에 따른 메시지 처리
	        if (result == 1) {
	            this.pw.print("<script>alert('정상적으로 회원가입 되셨습니다.');location.href='./login';</script>");
	        } else {
	            this.pw.print("<script>alert('회원가입에 실패하였습니다. 잠시 후 다시 시도해주세요.');history.back();</script>");
	        }
	    } catch (DuplicateKeyException e) {
	        this.pw = res.getWriter();
	        this.pw.print("<script>alert('이 전화번호는 이미 등록되어 있습니다.');history.back();</script>");
	    } catch (Exception e) {
	        this.pw = res.getWriter();
	        this.pw.print("<script>alert('서버 오류가 발생했습니다.');history.back();</script>");
	    } finally {
	        this.pw.close();
	    }
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
