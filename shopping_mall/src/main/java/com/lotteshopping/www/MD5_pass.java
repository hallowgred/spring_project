package com.lotteshopping.www;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;


//�н����带 md5���·� ��ȯ�ϴ� �޼ҵ�
//@Repository("md5pass")
abstract class MD5_pass {
		
		public String md5_making(String upass) {
			StringBuilder sb= new StringBuilder(); 	//return�� ���Ǵ� ��ü
			try {
				MessageDigest md = MessageDigest.getInstance("md5");
				md.update(upass.getBytes());
				for(byte bt : md.digest()) {
					sb.append(String.format("%x", bt));
				}
			}catch(Exception e) {
				sb.append("���ڰ� ���� �߻����� ��������");
			}
			return sb.toString();
		}
	
}
