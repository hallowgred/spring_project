package com.lotteshopping.www;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;


//패스워드를 md5형태로 변환하는 메소드
//@Repository("md5pass")
abstract class MD5_pass {
		
		public String md5_making(String upass) {
			StringBuilder sb= new StringBuilder(); 	//return에 사용되는 객체
			try {
				MessageDigest md = MessageDigest.getInstance("md5");
				md.update(upass.getBytes());
				for(byte bt : md.digest()) {
					sb.append(String.format("%x", bt));
				}
			}catch(Exception e) {
				sb.append("인자값 오류 발생으로 생성실패");
			}
			return sb.toString();
		}
	
}
