function terms_ajax(){
	var http,result;
	http=new XMLHttpRequest();
	http.onreadystatechange=function(){
		if(http.readyState==4&&http.status==200){
			 result=JSON.parse(this.response);
					
					document.getElementById("terms").append(result["terms"]);
					document.getElementById("personal_information").append(result["personal_information"]);
		}
	}
	http.open("get","./terms_load",true);
	http.send();	
	}

terms_ajax();




document.querySelector("#termsAgree1").addEventListener("click",function(){
	const a = document.querySelector("#termsAgree1").checked;
	const b = document.querySelector("#termsAgree2").checked;
	if(a==true&&b==true){
		document.querySelector("#btnNextStep").style.backgroundColor = 'yellow';
		document.querySelector("#btnNextStep").addEventListener("click",function(){
			agree1_frm.method="get";
			agree1_frm.action="./join";
			agree1_frm.submit();
		})
	}
	
})

document.querySelector("#termsAgree2").addEventListener("click",function(){
	const a = document.querySelector("#termsAgree1").checked;
	const b = document.querySelector("#termsAgree2").checked;
		if(a==true&&b==true){
		document.querySelector("#btnNextStep").style.backgroundColor = 'yellow';
		document.querySelector("#btnNextStep").addEventListener("click",function(){
			agree1_frm.method="get";
			agree1_frm.action="./join";
			agree1_frm.submit();
		})
	}else{
		document.querySelector("#btnNextStep").style.backgroundColor = 'Lignt grey';
	}
})


if(count==1){
	agree1_frm.method="get";
	agree1_frm.action="./join";
};



