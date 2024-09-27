package shopping;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import shopping_admin.shopping_module;


//Controller part
@Controller
public class shopping_Controller {

	PrintWriter pw=null;
	
	@Resource(name =  "shopping_module2")
	private shopping_Service ss;
	
	@Resource(name =  "shopping_module")
	private shopping_module sm;
	
	@PostMapping("/email_cc")
	public void email_cc(@RequestBody String email) {
		
	}
	
	
	
	@PostMapping("/join_idcheck")
	public void join_idcheck(@RequestBody String mid,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		String callback = ss.idck_join(mid);
		this.pw.print(callback);
		this.pw.close();
	}
	
	@RequestMapping("agree")
	public String agree() throws Exception{
		return null;
	}
	
	@RequestMapping("join")
	public String join() {
		return null;
	}
}
