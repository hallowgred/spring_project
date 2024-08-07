function stat_change(z,s){
	if(z=="정지"){
		if(confirm(s+"님을 정지 시키시겠습니까?")){
			member_list_frm.mstat.value="정지";
			go_change_stat();
		}
	}else{
		if(confirm(s+"님을 해제 시키시겠습니까?")){
			member_list_frm.mstat.value="정상";
			go_change_stat();	
		}
	}
}
function go_change_stat(){
	member_list_frm.method="post";
	member_list_frm.action="./change_stat";
	member_list_frm.submit();	
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
	