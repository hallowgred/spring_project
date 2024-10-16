document.querySelector("#product_make").addEventListener("click",function(){
	location.href="./product_write";
});

// 기존 JavaScript 코드에 추가
document.querySelector("#searchProduct").addEventListener("submit", function(event) {
    // 기본 폼 제출 동작 방지
    event.preventDefault();
	
    // 검색어 입력 필드와 선택한 옵션 가져오기
    var searchType = document.querySelector(".p_select1").value; // 검색 타입
    var searchKeyword = document.querySelector(".p_input1").value; // 검색어
    // 검색어가 비어 있는지 확인
    if (searchKeyword=="") {
        alert("검색어를 입력해 주세요!");
        return; // 빈 검색어면 종료
    }

    // 검색어가 유효하면 폼을 제출
	searchProduct.action="./product_list";
	searchProduct.method="get";
    this.submit(); // 폼 제출
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

