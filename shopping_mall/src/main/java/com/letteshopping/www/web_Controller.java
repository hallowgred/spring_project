package com.letteshopping.www;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class web_Controller {
	
	//@SessionAttribute : session이 이미 등록 되어있을 경우 해당 정보를 가져올 수 있음
	@GetMapping("/restapi.do")
	public String restapi(@SessionAttribute(name = "mid",required = false) String mid )throws Exception{
		if(mid==null) {
			System.out.println("로그인 해야만 결제내역을 확인 하실수 있습니다.");
		}else {
			System.out.println("결제 내역은 다음과 같습니다.");
		}
		
			
		return null;
	}
	//HttpSession: interface를 활용하여,세션을 빠르게 구현한느 방식 스타일
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
		System.out.println(ja.get(0));
		JSONArray ja2 = ja.getJSONArray(0);
		System.out.println(ja2.get(0));
		this.pw.print("ok");
		this.pw.close();
		return null;
	}
	
	
	
	
	//@RequestBody : front�뿉�꽌 JSON.Stringify濡� 蹂대깉湲� �븣臾몄뿉 
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
	
	
	//@RequestBody : GET/POST(X) JSON湲곕컲�씪 寃쎌슦
	//@ResponseBody : 誘몃뵒�뼱 ���엯,�뙆�씪誘명꽣 ���엯  �떒, �씤�옄媛믪뿉�꽌�뒗 �궗�슜X 
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@GetMapping("/ajaxok.do")
	//ajax�넻�떊 CORS 諛⑹떇
	//@RequestParam : 諛곗뿴�쓣 �씠�슜�븯�뿬 ���몴�궎濡� �쟾�떖 �삉�뒗 ���몴�궎�뾾�씠 蹂댁“�궎濡� �쟾�떖 �맆 寃쎌슦 �궗�슜 媛��뒫
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
