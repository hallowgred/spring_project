function approval(z){
	if(confirm("관리자 승인해제 하시겠습니까?")){
		approval_frm.master.value=1;
	approval_frm.sidx.value=z;
	approval_frm.method="post";
	approval_frm.action="./approval";
	approval_frm.submit();
	}	
}

function approval1(z){
	console.log(z);
	approval_frm.master.value=0;
	approval_frm.sidx.value=z;
	if(confirm("관리자 승인을 진행 하시겠습니까?")){
	approval_frm.method="post";
	approval_frm.action="./approval";
	approval_frm.submit();
}
}

