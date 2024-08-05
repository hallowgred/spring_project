document.querySelector("#pro_list").addEventListener("click",function(){
	location.href="./product_write";
});


document.querySelector("#cate_list").addEventListener("click",function(){
	location.href="./cate_write";
});



document.querySelector("#delete_cate").addEventListener("click",function(){
	var a = document.getElementsByClassName("delete_categorys");
	var w=0;
	while(w<a.length){
	if(a[w].checked!=false){
	if(confirm("삭제후 복구 하실수 없습니다. 정말 삭제하시겠습니까?")){
		delete_cate_frm.method="post";
		delete_cate_frm.action="./delete_cate";
		delete_cate_frm.submit();
	}
		}else{
		alert("삭제할 카테고리를 선택해주세요!");
		break;
	}
		w++;
	}
});