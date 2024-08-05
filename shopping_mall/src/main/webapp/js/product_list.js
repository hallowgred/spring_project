document.querySelector("#product_make").addEventListener("click",function(){
	location.href="./product_write";
});


document.querySelector("#cate_list_go").addEventListener("click",function(){
	location.href="./cate_list";
});

document.querySelector("#delete_pro").addEventListener("click",function(){
	var a = document.getElementsByClassName("check_radio")[0].checked;
	if(a!=false){
	if(confirm("삭제후 복구 하실수 없습니다. 정말 삭제하시겠습니까?")){
		delete_frm.method="post";
		delete_frm.action="./delete_product";
		delete_frm.submit();
	}
	}else{
		alert("삭제할 상품을 선택해주세요!");
	}
});

