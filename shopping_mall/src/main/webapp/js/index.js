
//로그인
function login(){
	login_frm.method="post";
	login_frm.action="./login.do";
	login_frm.submit();
	
}

//회원가입 파트
function sing_up(){
	location.href="./add_master.jsp";
}

function go_sign_up(){
	add_admin.shp.value=add_admin.shp1.value+add_admin.shp2.value+add_admin.shp3.value;
	add_admin.spart.value=add_admin.spart1.value+"!"+add_admin.spart2.value;
	console.log(add_admin.spart.value);
	
	add_admin.method="post";
	add_admin.action="./signup.do";
	add_admin.submit();
	
}



$(()=>{
	document.querySelector(".btn_button").addEventListener("click",function(){
		const id= document.getElementById("sid").value;
		$.ajax({
			url:"./idcheck.do?sid="+id,
			type: "get",
			cache:false,
			dataType:"text",
			contentType : "application/x-www-form-urlencoded",
			//data : name,
			success : function($result){
				if($result=="ok"){
					document.querySelector("#asd").innerHTML="<li>사용가능한 아이디 입니다.</li>";
				}
			},error:function(){
				console.log("error");
			}
		})
	});
})