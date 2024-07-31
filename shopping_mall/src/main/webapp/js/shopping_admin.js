function approval(){
if(approval_frm.approval_master.value==0){
confirm("관리자 승인을 진행 하시겠습니까?");
}else{
confirm("관리자 승인해제 하시겠습니까?");
}
	approval_frm.method="post";
	approval_frm.action="./approval";
	approval_frm.submit();
	
}
