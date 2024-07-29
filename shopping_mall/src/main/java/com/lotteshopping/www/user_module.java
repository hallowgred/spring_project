package com.lotteshopping.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.attribute.standard.MediaSize.NA;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//user table (select,insert,update,delete)
@Repository("user_module")
public class user_module {
	
	@Resource(name = "template")
	private SqlSessionTemplate tm;
	
	public ArrayList<Object> search_id(String uname,String uemail) {
		ArrayList<Object> onedate= new ArrayList();
		Map<String, String> keycode = new HashMap<String,String>();
		keycode.put("part", "1");
		keycode.put("uname", uname);
		keycode.put("uemail", uemail);
		user_dao dao = tm.selectOne("shopping_mall.search",keycode);
		System.out.println(dao.getUid());
		return onedate;
	}
	
	
}
