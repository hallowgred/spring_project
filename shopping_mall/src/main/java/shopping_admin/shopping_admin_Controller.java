package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class shopping_admin_Controller {

	@Resource(name =  "shopping_module")
	private shopping_module sm;
	
	PrintWriter pw = null;
	
	//공지사항 수정페이지
	@PostMapping("/notice_modify")
	public void notice_modify(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,shopping_notice_dao dao) {
		res.setContentType("text/html;charset=utf-8");
		String re="";
		try {
			this.pw=res.getWriter();
			if(list==null) {
				re="<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>";
			}else{
				int result = sm.modify_notice(dao);
				if(result==1) {
					re="<script>alert('정상적으로 수정되었습니다.');location.href='./notice_list';</script>";
				}else {
					re="<script>alert('데이터 오류로 인하여 수정하지 못하였습니다.');history.back();</script>";
				}
			}
		}catch(Exception e) {
			re="<script>alert('오류가 발생하여 수정하지 못하였습니다.');history.back();</script>";
		}finally {
			this.pw.print(re);
			this.pw.close();
		}
	}
	
	//공지사항 단일 페이지 출력
	@PostMapping("/notice_view")
	public String notice_view(@RequestParam(name = "one_nidx",required = false) String idx,@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		try{
			if(list==null) {
				this.pw=res.getWriter();
				this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
				this.pw.close();
			}else {
				if(idx!=null) {
				ArrayList<Object>arr=sm.notice_one_page(idx);
				m.addAttribute("notice_one_list",arr);
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return "notice_view";
	}
	
	
	//공지사항 삭제 
	@PostMapping("/notice_delete")
	public String notice_delete(@RequestParam(name = "idx",required = false) String idx[],@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,HttpServletRequest req)throws Exception {
		res.setContentType("text/html;charset=utf-8");
		String re ="";
		try {
			this.pw=res.getWriter();
			if(list==null) {
			re="<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>";	
			}else {
				int callback = sm.delete_notice(idx,req);
				if(callback==1) {
				re="<script>alert('정상적으로 삭제 되었습니다.');location.href='./notice_list';</script>";
				}else {
					re="<script>alert('데이터 오류로 인하여 삭제하지 못하였습니다.');history.back();</script>";
					}
				}
		}catch(Exception e) {
			re="<script>alert('오류가 발생하여 삭제하지 못하였습니다.');history.back();</script>";
			System.out.println(e);
		}finally {
			if(this.pw!=null) {
				this.pw.print(re);
			this.pw.close();
			}
		}
		return null;
	}
	
	//공지사항 작성
	@PostMapping("/notice_write1")
	public String notice_write1(shopping_notice_dao dao,@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,HttpServletRequest req,@RequestParam("nfiles") MultipartFile f[]) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		String re = "";
		this.pw=res.getWriter();
		try {
			if(list==null) {
				re="<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>";
			}else {		
				int call= sm.write_notice(dao,f,req);
				if(call==1) {
					re="<script>alert('정상적으로 등록 되었습니다.');location.href='./notice_list';</script>";
				}else {
					re="<script>alert('데이터 오류로 인하여 등록하지 못하였습니다. 잠시 후 다시 시도해주세요.');history.back();</script>";
				}
			}
		}catch(Exception e) {
			re="<script>alert('오류가 발생하여 등록하지 못하였습니다.');history.back();</script>";
		}finally {
			if(this.pw!=null) {
				this.pw.print(re);
				this.pw.close();
			}
		}
		return null;
	}
	
	
	//공지사항 작성 페이지
	@RequestMapping("/notice_write")
	public String notice_write(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res) throws Exception{
		try {
			if(list==null) {
				this.pw =res.getWriter();
				this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
				this.pw.close();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return "notice_write";
	}
	
	//공지사항 페이지 로드
	@RequestMapping("/notice_list")
	public String notice_list(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,shopping_notice_dao dao,Model m) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		try {
			if(list==null) {
				this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
				this.pw.close();
			}else {
				if(dao!=null) {
					List<Object> ar=sm.notice_list(dao);
					m.addAttribute("notice_list",ar);
				}
			}			
		}catch(Exception e) {
			System.out.println(e);
		}
		return "notice_list";
	}
	
	//ajax로 약관 가져오기
	@GetMapping("/terms_load")
	public String terms_load(HttpServletRequest req,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		JSONObject jo = sm.load_terms(req);
		this.pw.print(jo);
		this.pw.close();
		return null;
	}
	
	
	//회원 약관 로드
	@PostMapping("/terms")
	public String terms(@RequestParam(value = "",required = false) String terms,@RequestParam(value = "",required = false)  String personal_information,HttpServletRequest req,@RequestParam(value = "",required = false) int hidden_no,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		try {
			if(hidden_no==1) {
				sm.modi_terms("terms.txt",terms, req);
			}else {
				sm.modi_terms("personal_information.txt", personal_information, req);			
			}
			this.pw.print("<script>alert('정상적으로 변경되었습니다.');location.href='./shop_member_list';</script>");
		}catch(Exception e) {
			this.pw.print("<script>alert('데이터 오류로 인하여 수정하지 못하였습니다.');history.back();</script>");
			System.out.println(e);
		}finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//쇼핑몰 회원관리 로드
		@RequestMapping("/shop_member_list")
		public String member_list(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception{
			res.setContentType("text/html;charset=utf-8");
			try {
				if(list==null) {
					this.pw=res.getWriter();
					this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
					this.pw.close();
				}else {
					List<Object> ar =sm.member_list();
					if(ar!=null) {
						//m.addAttribute("terms_list",arr);
						m.addAttribute("member_list",ar);
					}
				}			
			}catch(Exception e) {
				System.out.println(e);
			}
			return "./shop_member_list";
		}
	
	
	
	//회원 상태 변경 파트
	@PostMapping("/change_stat")
	public String change_stat(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,shopping_member_dao dao) throws Exception{
		String re="";
		res.setContentType("text/html;charset=utf-8");
		try {
		this.pw=res.getWriter();
		if(list==null) {
			re="<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>";
		}else if(dao!=null){
			int call= sm.stat_change(dao);
			if(call==1) {
				re="<script>alert('정상적으로 변경 되었습니다.');location.href='./shop_member_list';</script>";
				}else {
					re="<script>alert('데이터 오류로 인하여 변경에 실패하였습니다.');history.back();</script>";
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
			}finally {
				if(this.pw!=null) {
					this.pw.print(re);
					this.pw.close();
				}
			}
		return null;
	}
	
	
	
	
	//상품삭제 파트
	@PostMapping("/delete_product")
	public String delete_product(@RequestParam("delete_pidx") String[] delete_pidx,@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res) throws Exception{
		String re="";
		res.setContentType("text/html;charset=utf-8");
		try {
		this.pw=res.getWriter();
		if(list==null) {
			re="<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>";

		}else {
			int call= sm.delete_pro(delete_pidx);
			if(call==1) {
				re="<script>alert('정상적으로 삭제 되었습니다.');location.href='./product_list';</script>";
			}else {
				re="<script>alert('데이터 오류로 인하여 삭제되지 않았습니다.');history.back()';</script>";
			}
		}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(this.pw!=null) {
				this.pw.print(re);
				this.pw.close();
			}
		}
		return null;
	}
	
	
	
	//상품 리스트 출력
	@RequestMapping("/product_list")
	public String product_list(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		try {
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
		this.pw.close();
		}else {
			List<Object> arr=sm.productlist();
			if(arr!=null) {
				m.addAttribute("product_list",arr);				
			}
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return "product_list";
	}
	
	
	//상품코드 중복체크
	@PostMapping("/pcode_ck")
	public String pcode_ck(@RequestBody String data,HttpServletResponse res) throws Exception{
		String re= "";
		try {
			this.pw=res.getWriter();
		int call = sm.pcode_ck1(data.split("=")[0]);
		if(call==1) {
			re="사용할 수 없는 상품코드 입니다.";
		}else{
			re="사용할 가능한 상품코드입니다.";
		}
		}catch(Exception e) {
			re="사용할 가능한 상품코드입니다.";	
		}finally {
			if(this.pw!=null) {
				this.pw.print(re);
				this.pw.close();
			}
		}
		
		return null;
	}
	
	
	//상품 등록 페이지
	@PostMapping("/product_write")
	public String product_write(@SessionAttribute(name = "list",required = false) String list,@RequestParam("thumbnail1") MultipartFile f[],HttpServletRequest req ,shopping_product_dao dao,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		String re = "";
		try {
			if(list==null) {
				re="<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>";
			}else {
			if(dao!=null) {
				int call = sm.make_product(dao,f,req);
				if(call==1) {
					re="<script>alert('정상적으로 상품등록 되었습니다.');location.href='./product_list';</script>";
				}else {
					re="<script>alert('데이터 오류로 인하여 등록하지 못하였습니다.');history.back();</script>";	
				}
			}else {
				re="<script>alert('데이터 오류로 인하여 등록하지 못하였습니다.');history.back();</script>";	
			}
			}
		}catch(Exception e) {
			re="<script>alert('데이터 오류로 인하여 등록하지 못하였습니다.');history.back();</script>";
			System.out.println(e);
		}finally {
			if(this.pw!=null) {
				this.pw.print(re);
				this.pw.close();
			}
		}
		return "product_list";
	}
	
	
	
	
	//첫페이지 로그인 화면
	@GetMapping("/admin")
	public String home() {
		return "index";
	}
	
	//카테고리 등록
	@RequestMapping("/cate_write")
	public String cate_write(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}else {
			List<String> re = sm.arr_lcode();
			if(re!=null) {
				m.addAttribute("re",re);
			}
		}
		return "cate_write";
	}
	
	
	//신규상품 등록
	@RequestMapping("/product_write")
	public String product_write(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}else {
			List<String> arr= sm.arr_category();
			m.addAttribute("category",arr);
		}
		return "product_write";
	}
	
	
	//관리자 페이지 로드
	@RequestMapping("/add_master")
    public String addMaster() {
        return "add_master";
    }
	
	//쇼핑몰 기본설정 로드
	@RequestMapping("/siteinfo")
	public String shopping_settings(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다.');location.href='./admin';</script>");
		this.pw.close();
		}else {
			shopping_settings_dao dao= sm.sp_set_sel();
		if(dao!=null) {
		m.addAttribute("settings_list",dao.list());
		}
		}
		return "siteinfo";
	}
	
	
	
	
	//쇼핑몰 상품관리 로드
	@RequestMapping("/cate_list")
	public String cate_list(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,Model m,shopping_cate_dao dao) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		if(list==null) {
			this.pw=res.getWriter();
		this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
		this.pw.close();
		}else if(dao!=null){
		//이거 dao형태로 select list형태로 배열 받아야됨
			Map<String, Integer> a= new HashMap<String, Integer>();
			a.put("a", 1);
			a.put("b", 1);
		List<Object> arr2=sm.category2();
		m.addAttribute("cate_list",arr2);
		}
		return "cate_list";
	}
	
	//카테고리 생성 파트 
	@PostMapping("/cate_make")
	public void cate_make(@SessionAttribute(name = "list",required = false) String list,HttpServletResponse res,shopping_cate_dao dao) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		if(list==null) {
		this.pw.print("<script>alert('올바른 접근이 아닙니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
		}else {
			if(dao!=null) {
			int result = sm.cate_make(dao);
				if(result==1) {
				this.pw.print("<script>alert('정상적으로 등록 되었습니다.');location.href='./cate_list';</script>");
				}
				else {
					this.pw.print("<script>alert('데이터 오류로 인해 등록에 실패하였습니다.');history.back();</script>");	
				}
			}
		}
		if(this.pw!=null) {
			this.pw.close();
		}
		
	}
	
	//쇼핑몰 기본설정
	@PostMapping("/siteinfo_write")
	public String siteinfo_write(@ModelAttribute shopping_settings_dao dao,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		try {
		if(dao!=null) {
		int result = sm.sp_set(dao);
		if(result==1) {
			this.pw.print("<script>alert('정상적으로 저장 되었습니다. ');location.href='siteinfo';</script>");
			}
		}
		}catch(Exception e) {
			System.out.println(e);
			this.pw.print("<script>alert('데이터 오류로 인하여 저장하지 못하였습니다. 잠시후 다시 시도해주세요!');history.back();</script>");
		}finally {
			if(this.pw!=null) {
				this.pw.close();
			}
		}
		return null;
	}
	//관리자 승인파트
	@PostMapping("/approval")
	public String approval(int master,int sidx,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
	 	int result = sm.approval_module(master, sidx);
		if(result==1) {
			this.pw.print("<script>alert('정상적으로 변경 되었습니다.');location.href='./shopping_admin.do';</script>");
		}else {
			this.pw.print("<script>alert('변경중 오류 발생으로 실패하였습니다.');history.back();</script>");
		}this.pw.close();
		return null;
	}
	
	
	//로그아웃 파트
	@GetMapping("/logout.do")
	public String logout(@SessionAttribute(name = "list",required = false) String list,HttpServletRequest req,HttpServletResponse res) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		String result=sm.log_out(req);
		if(result=="ok") {
			this.pw.print("<script>alert('로그아웃 되셨습니다.');location.href='./admin';</script>");
		}
		this.pw.close();
		return null;
	}
	
	//리스트 출력 파트
	@GetMapping("/shopping_admin.do")
	public String list_print(@SessionAttribute(name = "list",required = false) ArrayList<Object> arr,HttpServletResponse res,Model m) throws Exception{
		this.pw=res.getWriter();
		res.setContentType("text/html;charset=utf-8");
		if(!arr.get(3).equals((Object)2)) {
			this.pw.print("<script>alert('잘못된 접근입니다. 로그인 이후 이용해주세요!');location.href='./admin';</script>");
			this.pw.close();
		}else {
			List<shopping_admin_dao> ar =sm.admini();
			m.addAttribute("admin_lists",ar);
		}
		return "shopping_admin";
	}
	
	
	
	//로그인 파트
	@PostMapping("/shopping_admin.do")
	public String login_(String sid,String spass,shopping_admin_dao dao,HttpServletResponse res,HttpServletRequest req) throws Exception{
		try {
			res.setContentType("text/html;charset=utf-8");
			this.pw=res.getWriter();
			ArrayList<Object> callback=sm.login(sid, spass,res,dao);	
			if(callback!=null) {
			if(callback.get(3)==(Object)0) {
			this.pw.print("<script>alert('승인되지 않은 사용자입니다. 승인 이후 로그인 가능합니다.');history.back();</script>");	
			}else{
			HttpSession hs = req.getSession();
			hs.setMaxInactiveInterval(1800);
			hs.setAttribute("list", callback);
				this.pw.print("<script>alert('로그인 되셨습니다!');location.href='./shopping_admin.do';</script>");
			}
			}else {
				this.pw.print("<script>alert('아이디와 비밀번호를 확인해주세요!');history.back();</script>");
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(this.pw!=null) {
			this.pw.close();
			}
		}
		return null;
	}
	
	//가입등록
	@PostMapping("/signup.do")
	public String sign_up(shopping_admin_dao dao,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw=res.getWriter();
		int callback=sm.signup(dao);
		if(callback==1) {
			this.pw.print("<script>alert('가입등록이 완료되었습니다. 로그인은 관리자승인 이후 가능합니다.');location.href='./admin';</script>");
		}else {
			this.pw.print("<script>alert('데이터 오류로 인하여 가입등록에 실패하였습니다.');history.back();</script>");
		}
		this.pw.close();
		return null;
	}
	
	//아이디 중복체크
	@GetMapping("/idcheck.do")
	public String idcheck(String sid,HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		try{
		 String callback = sm.idck(sid);
		if(callback=="ok") {
			this.pw.print("ok");
		}
		else {
			this.pw.print("error");
		}
		}catch(Exception e) {
			
		}
		finally {
			this.pw.close();
		}
		return null;
	}
	
	
}
