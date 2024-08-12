
function go_noticewrite(){
	notice_list_frm.action="./notice_write";
	notice_list_frm.submit();
}


function go_noticedelete(){
if(confirm("정말 삭제하시겠습니까? 삭제 후 복구하실수 없습니다.")){
	notice_list_frm.method="post";
	notice_list_frm.action="./notice_delete";
	notice_list_frm.submit();
	}
}

function go_one_page(z){
notice_list_frm.one_nidx.value=z;
	notice_list_frm.method="post";
	notice_list_frm.action="./notice_view";
	notice_list_frm.submit();
}