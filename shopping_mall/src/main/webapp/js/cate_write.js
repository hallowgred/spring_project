document.querySelector("#cate_list").addEventListener("click",function(){
	location.href="./cate_list";
});

document.querySelector("#cate_make").addEventListener("click",function(){
		cate_write_frm.method="post";
		cate_write_frm.action="./cate_make";
		cate_write_frm.submit();
});