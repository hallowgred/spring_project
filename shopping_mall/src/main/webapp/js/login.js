document.querySelector("#login").addEventListener("click", function(event) {
    event.preventDefault(); // 기본 submit 방지
    var login_form = document.getElementById("login_form");
    login_form.method = "post";
    login_form.action = "./loginok";
    
    // saveid 체크박스의 값 설정
    var saveidCheckbox = document.getElementById("idsave");
    if (saveidCheckbox.checked) {
        login_form.saveid.value = "Y"; // 체크된 경우
    } else {
        login_form.saveid.value = "N"; // 체크 안 된 경우
    }
    
    login_form.submit();
});
