package com.lotteshopping.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class web_Controller extends MD5_pass{
	
	//@Resource(name = "md5pass")
	//private MD5_pass md;
	
	@Resource(name = "user_module")
	private user_module um;
	
	//DAO => @ModelAttribute
	//DAO없이 사용시 => 자료형 객체 or @RequestParam을 이요해서 사용
	
	@PostMapping("/idsearch.do")	//id찾기
	public String idsearch(String[] uname,String uemail,HttpServletResponse res)throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		try {
		if(uname==null||uemail==null) {
			this.pw.print("<script>alert('올바른 접근방식이 아닙니다.');history.back();</script>");	
		}else {
			ArrayList<Object> callback =um.search_id(uname[0], uemail);
		}
		}catch(Exception e) {
			System.out.println(e);
			this.pw.print("<script>alert('Database문제로 인하여 해당 정보가 확인 되지 않았습니다.');history.back();</script>");
		}
		finally {
		this.pw.close();	
		}
		return null;
	}
	
	
	@PostMapping("/passmodify.do")	//password �?�?
	public String passmodify() {
		
		return null;
	}
	
	
	
	//?��?��?��?�� �?�? ?���?�? 체크(MD5)
	@GetMapping("/passwd.do")
	public String passwd() {
	//MD5 : ?��?���??��, 로그?��, ?��?��?��?�� �?�?, 1:1문의, ?��?��게시?��, ?��?��구매...
		String pw= "a1234";
		String result = this.md5_making(pw);
		System.out.println(result);
		return null;
	}
	
	
	
	
	
	
	
	
	//@SessionAttribute : session?�� ?���? ?���? ?��?��?��?�� 경우 ?��?�� ?��보�?? �??��?�� ?�� ?��?��
	@GetMapping("/restapi.do")
	public String restapi(@SessionAttribute(name = "mid",required = false) String mid )throws Exception{
		if(mid==null) {
			System.out.println("로그?�� ?��?���? 결제?��?��?�� ?��?�� ?��?��?�� ?��?��?��?��.");
		}else {
			System.out.println("결제 ?��?��?? ?��?���? 같습?��?��.");
		}
		
			
		return null;
	}
	//HttpSession: interface�? ?��?��?��?��,?��?��?�� 빠르�? 구현?��?�� 방식 ?��???��
	@PostMapping("/loginok.do")
	public String loginok(@RequestParam String mid,HttpSession se) {
		se.setAttribute("mid", mid);
		se.setMaxInactiveInterval(1800);
		return null;
	}
	
	/*
	@PostMapping("/loginok.do")
	public String loginok(String mid,HttpServletRequest req) {
		HttpSession se= req.getSession();
		se.setAttribute("mid", mid);
		se.setMaxInactiveInterval(1800);
		System.out.println(mid);
		return null;
	}
	*/
	
	
	/*
	@GetMapping("/ajaxok3.do")
	public String ajaxok3(@ String al) {
		System.out.println(al);
		return null;
	}
	*/
	
	
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String data,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		System.out.println(data);
		JSONArray ja = new JSONArray(data);;
		//System.out.println(ja.get(0));
		//JSONArray ja2 = ja.getJSONArray(0);
		//System.out.println(ja2.get(0));
		JSONObject jo = ja.getJSONObject(0);
		System.out.println(jo.get("product"));
		this.pw.print("ok");
		this.pw.close();
		return null;
	}
	
	
	
	
	//@RequestBody : front�뿉�꽌 JSON.Stringify濡� 蹂�?깉湲�? �븣?��몄뿉 
	PrintWriter pw=null;
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public String ajaxok2(@RequestBody String all_data,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		JSONObject jo = new JSONObject(all_data);
		//System.out.println(jo.get("all_data"));
		
		/*
		JSONArray ja = jo.getJSONArray("all_data"); 
		//System.out.println(ja.get(0));
		JSONObject jo2 = new JSONObject();
		jo2.put("resut", "ok");
		this.pw.print(jo2);
		this.pw.close();
		*/
		return null;
	}
	
	
	//@RequestBody : GET/POST(X) JSON湲곕컲�?�� 寃쎌?��
	//@ResponseBody : 誘몃뵒��? ���엯,�뙆�씪誘명�? ���엯  �떒, �씤�옄媛�?�뿉�꽌�뒗 �궗�슜X 
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@GetMapping("/ajaxok.do")
	//ajax�넻�떊 CORS 諛⑹?��
	//@RequestParam : 諛곗뿴�?�� �씠�슜�븯�뿬 ���몴�궎濡� �쟾�떖 �삉�뒗 ���몴�궎�뾾�씠 蹂댁?��궎濡�? �쟾�떖 �맆 寃쎌?�� �궗�슜 媛��뒫
	public String ajaxok(@RequestParam(value = "alldata") List<String> alldata,HttpServletResponse res) throws Exception {
		System.out.println(alldata);
		System.out.println(alldata.get(0));
		this.pw=res.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("result", "ok");
		this.pw.print(jo);
		this.pw.close();
		return null;
	}
	
}
