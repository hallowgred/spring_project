function go_noticewrite(){
	location.href="./notice_write";
}

function go_noticedelete(){
	notice_list_frm.method="post";
	notice_list_frm.action="./notice_delete";
	notice_list_frm.submit();
}

