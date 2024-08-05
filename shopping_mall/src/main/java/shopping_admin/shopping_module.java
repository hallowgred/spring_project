package shopping_admin;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Repository("shopping_module")
public class shopping_module {

	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	//상품 삭제 파트
	public int delete_pro(String[] delete_pidx) {
		int w=0;
		int count=0;
		int result=0;
		while(w<delete_pidx.length) {
		int re= tm2.delete("shopping.delete_pro",delete_pidx[w]);
		if(re==1) {
			count++;
		}
		w++;
		}if(count==delete_pidx.length) {
			result=1;
		}
		return result;
	}
	
	
	//상품 리스트 출력
	public List<Object> productlist() {
		return tm2.selectList("shopping.pro_sel");
	}
	
	//상품코드 중복체크
	public int pcode_ck1(String pcode) {
		return tm2.selectOne("shopping.pcode_ck",pcode);
	}
	
	//대메뉴코드 배열만들기
	public List<String> arr_lcode() {
		Map<String, Integer> a = new HashMap<String,Integer>();
		a.put("part",2);
		return tm2.selectList("shopping.cate_sel",a);
	}
	
	//카테고리만 배열만들기
	public List<String> arr_category() {
		Map<String, Integer> a = new HashMap<String,Integer>();
		a.put("part",1);
		return tm2.selectList("shopping.cate_sel",a);
	}
	//파일 이름 변경파트
	public String rename() {
		Date day=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(day);
		int no = (int)Math.ceil(Math.random()*1000);
		String datacode= today+no;
		return datacode;
	}
	
	//상품 등록 파트
	public int make_product(shopping_product_dao dao,MultipartFile f[],HttpServletRequest req) {
		try {
		String url = req.getServletContext().getRealPath("/upload/");
		int w=0;
		ArrayList<String> al= new ArrayList<String>();	
		ArrayList<String> al2= new ArrayList<String>();	
		while(w<f.length) {
			if(f[w].getSize()>0) {
			al.add(f[w].getOriginalFilename());
			int com=f[w].getOriginalFilename().indexOf(".");
			String wd =f[w].getOriginalFilename().substring(com);
			String refilename=this.rename()+wd;
			al2.add(refilename);
			//웹 디렉토리에 파일명이 변경되어서 저장됨
			FileCopyUtils.copy(f[w].getBytes(), new File(url+refilename));
			}
			w++;
		}
		int ww=0;
		String thumbnail="";
		while(ww<al.size()) {
			thumbnail+=al.get(ww)+"-"+al2.get(ww)+"/";
			ww++;
		}
		dao.setThumbnail(thumbnail);
		}catch(Exception e) {
		}
		return tm2.insert("shopping.make_product",dao);
	}
	
	
	//카테고리 생성
	public int cate_make(shopping_cate_dao dao) {
		return tm2.insert("shopping.cate_make",dao);
	}
	
	
	//쇼핑몰 기본설정 데이터 select
	public shopping_settings_dao sp_set_sel() {
		return tm2.selectOne("shopping.sp_set_sel");
	}
	
	
	//쇼핑몰 기본설정
	public int sp_set(shopping_settings_dao dao){
		return dao.getHidx()!=0 ? tm2.update("shopping.update_settings_sp",dao) : tm2.insert("shopping.insert_settings_sp",dao);
	}
	
	//카테고리만 리스트 배열
	public List<Object> category() {
	 List<Object> ar =	tm2.selectList("shopping.category");
		return ar;
	}
	
	//카테고리 전제 리스트 배열
	public List<Object>  category2() {
		List<Object> ar = tm2.selectList("shopping.category2");	
		return ar;
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
