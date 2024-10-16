function terms_ajax() {
    var http, result;
    http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (http.readyState == 4 && http.status == 200) {
            result = JSON.parse(this.response);
            document.getElementById("terms").append(result["terms"]);
            document.getElementById("personal_information").append(result["personal_information"]);
        }
    };
    http.open("get", "./terms_load", true);
    http.send();
}

terms_ajax();

document.querySelector("#allAgree").addEventListener("click", function() {
    const isChecked = this.checked;
    document.querySelector("#termsAgree1").checked = isChecked;
    document.querySelector("#termsAgree2").checked = isChecked;
    checkTermsAgreement(); // 약관 동의 체크 확인
});

document.querySelector("#termsAgree1").addEventListener("click", function() {
    checkTermsAgreement();
});

document.querySelector("#termsAgree2").addEventListener("click", function() {
    checkTermsAgreement();
});

function checkTermsAgreement() {
    const a = document.querySelector("#termsAgree1").checked;
    const b = document.querySelector("#termsAgree2").checked;
    const btnNextStep = document.querySelector("#btnNextStep");

    if (a && b) {
        btnNextStep.style.backgroundColor = 'yellow';
        btnNextStep.removeEventListener("click", handleSubmit); // 이전 핸들러 제거
        btnNextStep.addEventListener("click", handleSubmit); // 새로운 핸들러 추가
    } else {
        btnNextStep.style.backgroundColor = 'lightgrey'; // 비활성화 시 색상 변경
        btnNextStep.removeEventListener("click", handleSubmit); // 핸들러 제거
    }
}

function handleSubmit() {
    agree1_frm.method = "get";
    agree1_frm.action = "./join";
    agree1_frm.submit();
}

// count 변수의 값이 1일 때도 제출
if (count == 1) {
    agree1_frm.method = "get";
    agree1_frm.action = "./join";
}
