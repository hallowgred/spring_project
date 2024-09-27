document.querySelector("#join_idck").addEventListener("click",function(){

var data = document.querySelector("#mid").value;

	fetch("./join_idcheck",{
	method:"POST",
	headers:{ 'Content-Type': 'application/JSON'},
	body:JSON.stringify(data)
	})
  .then(response => response.text())
  .then(data =>{
	document.querySelector("#idck").append("");
	console.log(data);
	document.querySelector("#id").innerHTML= "<span id='idck'>"+data+"</span>";
	}
)
  .catch(error => console.error('Error:', error));
});		



var num="";


document.querySelector("#memail").addEventListener("click",function(){

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
	document.querySelector("#idck").append("");
	console.log(data);
	document.querySelector("#id").innerHTML= "<span id='idck'>"+data+"</span>";
	}
)
  .catch(error => console.error('Error:', error));
});	