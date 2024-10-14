document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#join_idck").addEventListener("click", function() {
        var data = document.querySelector("#mid").value;

        fetch("./join_idcheck", {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ mid: data }) // 객체 형태로 수정
        })
        .then(response => response.text())
        .then(data => {
            let idckElement = document.querySelector("#idck");
            if (data=="사용가능한 아이디 입니다.") {
                idckElement.value=data; 
            } else {
                console.error("Element with ID 'idck' not found.");
            }
        })
        .catch(error => console.error('Error:', error));
    });
});




var num="";


document.querySelector("#memail").addEventListener("click",function(){
	
 num="";

var w=0;
while(w<8){
	num+=Math.ceil(Math.random()*9);
	w++;
}

var data = document.querySelector("#memail_text").value;
var a=[data,num];
	fetch("./email_cc",{
	method:"POST",
	headers:{ 'Content-Type': 'application/JSON'},
	body:JSON.stringify(a)
	})
  .then(response => response.text())
  .then(data =>{
	if(data=="ok"){
		document.getElementById("emailok").value="인증번호가 발송되었습니다.";
	}else{
		document.getElementById("emailok").value="이메일을 다시 확인해주세요.";
	}
	}
)
  .catch(error => console.error('Error:', error));
});	

document.querySelector("#btnNextStep").addEventListener("click",function(){
	join_form.action="./joinok";
	join_form.method="post";
	join_form.submit();
})
