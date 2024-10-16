document.addEventListener("DOMContentLoaded", function() {
    let idChecked = false; // 아이디 중복확인 상태
    let emailVerified = false; // 이메일 인증 상태

    document.querySelector("#join_idck").addEventListener("click", function() {
        var data = document.querySelector("#mid").value;

        fetch("./join_idcheck", {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ mid: data })
        })
        .then(response => response.text())
        .then(data => {
            let idckElement = document.querySelector("#idck");
            if (data === "사용가능한 아이디 입니다.") {
                idckElement.value = data;
                idChecked = true; // 아이디가 사용 가능할 경우 상태 변경
            } else {
                alert(data); // 사용 불가능한 경우 알림
                idChecked = false; // 사용 불가능할 경우 상태 유지
            }
        })
        .catch(error => console.error('Error:', error));
    });

    document.querySelector("#memail").addEventListener("click", function() {
        var num = generateRandomNumber(8);
        var data = document.querySelector("#memail_text").value;
        var a = [data, num];

        fetch("./email_cc", {
            method: "POST",
            headers: { 'Content-Type': 'application/JSON' },
            body: JSON.stringify(a)
        })
        .then(response => response.text())
        .then(data => {
            if (data === "ok") {
                document.getElementById("emailok").value = "인증번호가 발송되었습니다.";
                emailVerified = true; // 이메일 인증 상태 변경
            } else {
                document.getElementById("emailok").value = "이메일을 다시 확인해주세요.";
                emailVerified = false; // 인증 실패 시 상태 유지
            }
        })
        .catch(error => console.error('Error:', error));
    });

    document.querySelector("#btnNextStep").addEventListener("click", function() {
        if (validateForm()) {
            if (idChecked && emailVerified) { // 아이디 중복확인 및 이메일 인증 확인
                join_form.action = "./joinok";
                join_form.method = "post";
                join_form.submit();
            } else {
                alert("아이디 중복확인 및 이메일 인증을 완료해야 합니다.");
            }
        }
    });
});

// 랜덤 인증번호 생성
function generateRandomNumber(length) {
    var num = "";
    for (var w = 0; w < length; w++) {
        num += Math.ceil(Math.random() * 9);
    }
    return num;
}

// 회원가입 유효성 검사
function validateForm() {
    const id = document.querySelector("#mid").value;
    const password = document.querySelector("input[name='mpass']").value;
    const confirmPassword = document.querySelector("#mpass1").value; // 비밀번호 확인 입력
    const name = document.querySelector("input[name='mname']").value;
    const email = document.querySelector("#memail_text").value;
    const emailCode = document.querySelector("#email_ck").value;
    const phone = document.querySelector("input[name='mtel']").value;

    // 각 필드가 비어 있는지 확인하고 알림
    if (!id) {
        alert("아이디를 입력해 주세요.");
        return false;
    }
    if (!password) {
        alert("비밀번호를 입력해 주세요.");
        return false;
    }
    if (!confirmPassword) {
        alert("비밀번호 확인을 입력해 주세요.");
        return false;
    }
    if (!name) {
        alert("이름을 입력해 주세요.");
        return false;
    }
    if (!email) {
        alert("이메일을 입력해 주세요.");
        return false;
    }
    if (!emailCode) {
        alert("인증번호를 입력해 주세요.");
        return false;
    }
    if (!phone) {
        alert("전화번호를 입력해 주세요.");
        return false;
    }

    // 아이디 검사 (영문, 숫자)
    const idPattern = /^[a-zA-Z0-9]+$/;
    if (!idPattern.test(id)) {
        alert("아이디는 영문자와 숫자만 사용 가능합니다.");
        return false;
    }

    // 비밀번호 검사 (영문, 숫자, 특수문자)
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
    if (!passwordPattern.test(password)) {
        alert("비밀번호는 최소 6자 이상이어야 하며, 영문, 숫자, 특수문자를 포함해야 합니다.");
        return false;
    }

    // 비밀번호 확인 검사
    if (password !== confirmPassword) {
        alert("비밀번호 확인이 일치하지 않습니다.");
        return false;
    }

    // 이름 검사 (한글)
    const namePattern = /^[가-힣]+$/;
    if (!namePattern.test(name)) {
        alert("이름은 한글만 사용할 수 있습니다.");
        return false;
    }

    // 이메일 형식 검사
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
        alert("유효한 이메일 주소를 입력해주세요.");
        return false;
    }

    // 인증번호 검사 (8자리)
    if (emailCode.length !== 8) {
        alert("인증번호는 8자리입니다.");
        return false;
    }

    // 전화번호 검사 (숫자만)
    const phonePattern = /^\d+$/;
    if (!phonePattern.test(phone)) {
        alert("전화번호는 숫자만 입력하세요.");
        return false;
    }

    return true; // 모든 검사를 통과하면 true 반환
}
