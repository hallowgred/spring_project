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

function update_terms(){
	if(member_list_frm.tidx.value==""){		
	member_list_frm.tidx.value=0;
	console.log(member_list_frm.tidx.value);
	}
	if(confirm("약관을 변경하시겠습니까?")){
	member_list_frm.method="post"
	member_list_frm.action="./terms";
	member_list_frm.submit();	
	}
} 