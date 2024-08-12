document.querySelector("#notice_list").addEventListener("click",function(){
	location.href="./notice_list";
});


document.querySelector("#notice_modify").addEventListener("click",function(){
	notice_one_frm.method="post";
	notice_one_frm.action="./notice_modify";
	notice_one_frm.submit();
});


document.querySelector("#notice_del").addEventListener("click",function(event){
 if (!confirm("공지사항 데이터 삭제시 복구 되지 않습니다.")) {
       	event.preventDefault();
        return false; 
    }
	notice_one_frm.method="post";
	notice_one_frm.action="./notice_delete";
	notice_one_frm.submit();
	
});
