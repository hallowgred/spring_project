document.querySelector("#make_product").addEventListener("click",function(){
		
	if(confirm("상품을 등록하시겠습니까?")){
	const space=/^\s*$/;
		if(space.test(product_write_frm.cate_name.value)){
			alert("대메뉴 카테고리를 선택해주세요!");
		}else if(space.test(product_write_frm.pname.value)){
			alert("상품명을 입력해주세요!");
		}else if(space.test(product_write_frm.pex.value)){
			alert("상품 부가설명을 입력해주세요!");
		}else if(space.test(product_write_frm.price.value)){
			alert("판매가격을 입력해주세요!");
		}else if(space.test(product_write_frm.pea.value)){
			alert("상품재고를 입력해주세요!");
		}else if(document.getElementById("thumbnail1").files.length==0&&document.getElementById("thumbnail2").files.length==0&&document.getElementById("thumbnail3").files.length==0){
			alert("상품 대표이미지는 한가지 입력하셔야합니다!");
		}else if(space.test(product_write_frm.pro_exp.value)){
			alert("상품 상세설명을 입력해주세요!");
		}else{
		product_write_frm.method="post";
		product_write_frm.action="./product_write";
		product_write_frm.submit();
		}
	}
});



document.querySelector("#make_go_category").addEventListener("click",function(){
	location.href="./cate_write";
});

document.querySelector("#list_product").addEventListener("click",function(){
	location.href="./product_list";
});

pcode();

function pcode(){
product_write_frm.pcode.value="";
	var num="";
	var w=0
	while(w<7){
		var n = Math.ceil(Math.random()*9);
		num+=n;
	w++;
	}
	product_write_frm.pcode.value=num;
}


function pcode_ck(){
	var data= document.getElementById("pcode").value;
	var http=new XMLHttpRequest();
	http.onreadystatechange=function(){
		if(http.readyState==4&&http.status==200){
			document.getElementById("ajax_return").innerHTML=http.response;
		}
	};
	http.open("post","./pcode_ck",true);
	http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	http.send(data);
}

document.querySelector("#price").addEventListener("keyup",function(){
		document.getElementById("dis_price").value=0;
		var a = document.getElementById("price").value;
		var b = document.getElementById("discount").value;
		if(a==""){
		a=0;
		}else{
		if(a/b!=Infinity){
		document.getElementById("dis_price").value=Math.round(a/b);
			}
		}
});


document.querySelector("#discount").addEventListener("keyup",function(){
		document.getElementById("dis_price").value=0;
		var a = document.getElementById("price").value;
		var b = document.getElementById("discount").value;
		if(b==""){
		b=0;
		}else{
		if(a/b!=Infinity){
		document.getElementById("dis_price").value=Math.round(a/b);
		}
		}
});