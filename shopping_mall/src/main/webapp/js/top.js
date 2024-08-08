document.querySelector("#logout").addEventListener("click",function(){
		location.href="./logout.do";
});

document.querySelector(".top_to_home").addEventListener("click",function(){
		location.href="./admin";
});

document.querySelector("#sp_admini").addEventListener("click",function(){
	location.href="./shopping_admin.do";
});

document.querySelector("#sp_set").addEventListener("click",function(){
	location.href="./siteinfo";
});

document.querySelector("#sp_pro").addEventListener("click",function(){
	location.href="./product_list";
});

document.querySelector("#sp_mem").addEventListener("click",function(){
	location.href="./shop_member_list";
});		

document.querySelector("#sp_not").addEventListener("click",function(){
	location.href="./notice_list";
});