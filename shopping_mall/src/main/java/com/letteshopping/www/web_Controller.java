package com.letteshopping.www;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

@Controller
public class web_Controller {
	
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
	
	
	
	
	//@RequestBody : front에서 JSON.Stringify로 보냈기 때문에 
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
	
	
	//@RequestBody : GET/POST(X) JSON기반일 경우
	//@ResponseBody : 미디어 타입,파라미터 타입  단, 인자값에서는 사용X 
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@GetMapping("/ajaxok.do")
	//ajax통신 CORS 방식
	//@RequestParam : 배열을 이용하여 대표키로 전달 또는 대표키없이 보조키로 전달 될 경우 사용 가능
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
