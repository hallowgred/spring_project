function home(){
	location.href="./admin";
}


//로그인
function login(){
	if(login_frm.sid.value==""||login_frm.spass.value==""){
		alert("아이디와 비밀번호를 입력해주세요.");
		return false;
	}else{
	login_frm.method="post";
	login_frm.action="./login.do";
	return true;
	}
}

//회원가입 파트
function sing_up(){
	location.href="./add_master.jsp";
}





