package shopping;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shopping_admin.shopping_module;


//Controller part
@Controller
public class shopping_Controller {

	PrintWriter pw=null;
	
	@Resource(name =  "shopping_module")
	private shopping_module sm;
	
	@GetMapping("agree")
	public String agree() throws Exception{
		return null;
	}
	
	@GetMapping("join")
	public String join() {
		return null;
	}
}
