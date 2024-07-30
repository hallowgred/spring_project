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
					document.querySelector("#asd").innerHTML="사용가능한 아이디 입니다.";
				}else{
					document.querySelector("#asd").innerHTML="사용불가한 아이디 입니다.";
				}
			},error:function(){
				console.log("error");
			}
		})
	});
})
function cans(){
	location.href="./index.jsp";
}

function go_sign_up(){
	const hp = /\d{11}/;
	const En_ck=/^[a-zA-Z1-9]/;
	const KO_CK =/^[ㄱ-ㅎ가-힁]/;
	const NUM_CK=/[1-9]/;
	const anotation=/@./;
	add_admin.shp.value=add_admin.shp1.value+add_admin.shp2.value+add_admin.shp3.value;
	console.log(add_admin.shp.value.length);
	if(add_admin.sid.value==""){
		alert("사용하실 아이디를 입력해주세요!");
	}else if(!En_ck.test(add_admin.sid.value)){
		alert("아이디는 영문 또는 숫자만 사용가능합니다!");
	}else  if(add_admin.spass.value==""){
		alert("사용하실 비밀번호를 입력해주세요!");
	}else if(KO_CK.test(add_admin.spass.value)){
		alert("비밀번호는 한글을 사용할 수 없습니다!");
	}else if(add_admin.spass.value!=add_admin.spass_ck.value){
		alert("비밀번호가 일치하지 않습니다!");
	}else if(add_admin.sname.value==""){
		alert("이름을 입력해주세요!");
	}else if(add_admin.semail.value==""){
		alert("이메일을 입력해주세요!");
	}else if(!anotation.test(add_admin.semail.value)){
		alert("이메일을 정확히 입력해주세요!");
	}else if(add_admin.shp1.value==""||add_admin.shp2.value==""||add_admin.shp3.value==""){
		alert("전화번호를 입력해주세요!");
	}else if(!NUM_CK.test(add_admin.shp1.value)){
		alert("전화번호는 숫자만 입력해주세요!");
	}else if(!NUM_CK.test(add_admin.shp2.value)){
		alert("전화번호는 숫자만 입력해주세요!");
	}else if(!NUM_CK.test(add_admin.shp3.value)){
		alert("전화번호는 숫자만 입력해주세요!");
	}else if(add_admin.shp.value.length!="11"){
		alert("전화번호를 제대로 입력해주세요!");
	}else if(add_admin.spart1.value==""||add_admin.spart2.value==""){
		alert("담당부서 및 직책을 선택해주세요!");
	}
	else{
	
	add_admin.spart.value=add_admin.spart1.value+"!"+add_admin.spart2.value;
	console.log(add_admin.spart.value);
	
	add_admin.method="post";
	add_admin.action="./signup.do";
	add_admin.submit();
	} 
}