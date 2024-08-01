package shopping_admin;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("shopping_module")
public class shopping_module {

	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	//쇼핑몰 기본설정 저장취소 파트
	public int delete_write_info1(String hidx) {
		return tm2.delete("shopping.delete_write",hidx);
	}
	
	//쇼핑몰 기본설정 데이터 select
	public shopping_settings_dao sp_set_sel(shopping_settings_dao dao) {
		return tm2.selectOne("shopping.sp_set_sel",dao.getHidx());
	}
	
	
	//쇼핑몰 기본설정
	public int sp_set(shopping_settings_dao dao){
		return dao.getHidx()!=0 ? tm2.update("shopping.update_settings_sp",dao) : tm2.insert("shopping.insert_settings_sp",dao);
		//return tm2.insert("shopping.insert_settings_sp",dao);
	}
	
	
	
	//최고 관리자 리스트 배열 생성
	public List<shopping_admin_dao> admini(){
	List<shopping_admin_dao>dao =tm2.selectList("shopping.master_sel");	
		return dao;
	}
	
	//관리자 승인 파트
	public int approval_module(int master,int sidx) {
		Map<String, Integer> map =new HashMap<String, Integer>();
		if(master==1) {
		map.put("master", 0);
		}else {
			map.put("master", 1);	
		}
		map.put("sidx", sidx);
		return tm2.update("shopping.approval",map);
	}
	
	
	
	//로그아웃 파트
	public String log_out(HttpServletRequest req) {
		String result="";
		try {
		HttpSession hs= req.getSession();
		hs.invalidate();
		result="ok";
		}catch(Exception e) {	
		}
		return result;	
	}
	
	
	//로그인시 정보를 배열에 담기
	public ArrayList<Object> login(String sid, String spass, HttpServletResponse res, shopping_admin_dao dao) throws Exception {
	    ArrayList<Object> list = null;
	    res.setContentType("text/html;charset=utf-8");
	    String pass = pass_security(dao);
	    try {
	        Map<String, String> log = new HashMap<>();
	        log.put("spass", pass);
	        log.put("sid", sid);
	        shopping_admin_dao result = tm2.selectOne("shopping.login", log);
	        
	        if (result != null) {
	            list = result.lists();
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	        e.printStackTrace();
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
