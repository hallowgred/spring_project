package shopping;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("shopping_module2")
public class shopping_Service {

	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	public join_DTO login(loginEntity ett) {
	    return tm2.selectOne("shopping.select_login", ett);
	}

	
	
	public int joinMember(join_DTO dto) {
		int result = tm2.insert("shopping.insert_member",dto);
		return result;
	}
	
	public String idck_join(String mid) {
		String call="사용하실수 없는 아이디 입니다.";
		try {
		String remid= mid.replaceAll("\"","");
		 String a= tm2.selectOne("shopping.join_idck", remid);
		 if(a==null) {
			 call="사용가능한 아이디 입니다.";
		 }
		}catch(Exception e) {
		call="사용가능한 아이디 입니다.";
		System.out.println(e);
		}
		return call;
	}
	
}
