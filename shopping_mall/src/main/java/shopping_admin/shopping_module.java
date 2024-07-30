package shopping_admin;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttribute;

@Repository("shopping_module")
public class shopping_module {

	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	PrintWriter pw =null;
	//최고 관리자 리스트 배열 생성
	public ArrayList<ArrayList<Object>> admini(){
		shopping_admin_dao dao =(shopping_admin_dao) tm2.selectList("shopping.master_sel");
		ArrayList<ArrayList<Object>> li=dao.dou_list();
		return li;
	}
	
	
	
	//로그아웃 파트
	public String log_out(HttpServletRequest req) {
		String result="";
		try {
		HttpSession hs= req.getSession();
		hs.invalidate();
		result="ok";
		}catch(Exception e) {
			
		}finally {
			this.pw.close();
		}
		return result;	
	}
	
	
	//로그인시 정보를 배열에 담기
	public ArrayList<Object> login(String sid,String spass,HttpServletResponse res,shopping_admin_dao dao) throws Exception{
		ArrayList<Object> list=null;
		res.setContentType("text/html;charset=utf-8");
		String pass = pass_security(dao);
		
		this.pw=res.getWriter();
		try {
		Map<String, String> log = new HashMap<String, String>();
		log.put("spass", pass);
		log.put("sid", sid);
		dao = tm2.selectOne("shopping.login",log);
		list = dao.lists();
		}catch(Exception e) {
		this.pw.print("<script>alert('아이디와 비밀번호를 확인해주세요!');location.href='./admin';</script>");	
		}finally {
			this.pw.close();
		}
		return list;
	}
	
	//비밀번호 보안 sha3
	public String pass_security(shopping_admin_dao dao) {
		StringBuilder sb=null;
		try {
			MessageDigest sha3 = MessageDigest.getInstance("SHA-256");
			String data=dao.getSpass();
			byte[] pass= sha3.digest(data.getBytes());
			sb = new StringBuilder();
			for(byte b:pass) {
				sb.append(String.format("%016x", b));
			}
		}catch(Exception e) {
			
		}		
		return sb.toString();
	}
	
	//가입등록 
	public int signup(shopping_admin_dao dao) {
		dao.setSpass(pass_security(dao));
		return tm2.insert("shopping.signup",dao);
	}
	
	
	//아이디 중복체크
	public String idck(String sid) {
		String result="";
		try {
			shopping_admin_dao dao= tm2.selectOne("shopping.id_check",sid);
			if(dao.getSid()!=null) {
				result="error";
			}
		}catch(Exception e){
			result="ok";
		}	
		return result;
	}
	
}