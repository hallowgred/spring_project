function stat_change(z, s, midx) {
    console.log(z);
    var mstatInput = document.getElementById("mstat_" + midx); // 고유한 ID 사용
    if (z == "정지") {
        if (confirm(s + "님을 정지 시키시겠습니까?")) {
            mstatInput.value = "정지"; // 값 설정
            console.log("Changed mstat value to:", mstatInput.value); // 변경된 값 확인
            go_change_stat();
        }
    } else {
        if (confirm(s + "님을 해제 시키시겠습니까?")) {
            mstatInput.value = "정상"; // 값 설정
            console.log("Changed mstat value to:", mstatInput.value); // 변경된 값 확인
            go_change_stat();
        }
    }
}

function go_change_stat() {
    var mstatInputs = document.querySelectorAll("input[name='mstat']"); // 모든 mstat 입력 필드 선택
    mstatInputs.forEach(input => {
        console.log("Submitting mstat value:", input.value); // 제출 전에 값 확인
    });
    
    member_list_frm.method = "post";
    member_list_frm.action = "./change_stat";
    member_list_frm.submit(); // submit 호출
}


function update_terms(z){
	if(confirm("약관을 변경하시겠습니까?")){
		if(z==1){
			member_list_frm.hidden_no.value=1;
		}else{
			member_list_frm.hidden_no.value=0;
		}
		
		member_list_frm.method="post";
		member_list_frm.action="./terms";
		member_list_frm.submit();
	} 
}

function terms_ajax(){
	var http,result;
	http=new XMLHttpRequest();
	http.onreadystatechange=function(){
		if(http.readyState==4&&http.status==200){
			 result=JSON.parse(this.response);
					member_list_frm.terms.value=result["terms"];
					member_list_frm.personal_information.value=result["personal_information"];
		}
	}
	http.open("get","./terms_load",true);
	http.send();	
	}

terms_ajax();
	