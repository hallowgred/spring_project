function write_page_info(){
	const space =/^\s*$/;
	const KO = /^[ㄱ-ㅎ가-힣]+$/; 
	const num16 = /^[\d-]{1,16}$/;
	const mail_ck =/^[a-zA-Z0-9@.가-힣]+$/;
	const mail_ck2 = /(?=.*@)(?=.*\.).*/;
	if(space.test(settings_frm.hname.value)){
		alert("홈페이지 제목을 입력해주세요!");
	}else if(space.test(settings_frm.hemail.value)){
		alert("관리자 메일주소를 입력해주세요!");
	}else if(!mail_ck.test(settings_frm.hemail.value)){
		alert("이메일을 형식에 맞게 입력해주세요!");
	}else if(!mail_ck2.test(settings_frm.hemail.value)){
		alert("이메일을 형식에 맞게 입력해주세요!");
	}else if(space.test(settings_frm.hsignpoint.value)){
		alert("회원가입시 적립금을 입력해주세요!");
	}else if(space.test(settings_frm.hsignlev.value)){
		alert("회원가입시 권한레벨을 입력해주세요!");
	}else if(space.test(settings_frm.corname.value)){
		alert("회사명을 입력해주세요!");
	}else if(space.test(settings_frm.business_reg.value)){
		alert("사업자등록번호를 입력해주세요!");
	}else if(space.test(settings_frm.repname.value)){
		alert("대표자명을 입력해주세요!");
	}else if(space.test(settings_frm.rephp.value)){
		alert("대표전화번호를 입력해주세요!");
	}else if(space.test(settings_frm.workplace_mail.value)){
		alert("사업장 우편번호를 입력해주세요!");
	}else if(settings_frm.workplace_mail.value.length!=5){
		alert("사업장 우편번호는 5자리를 입력해주세요!");
	}else if(space.test(settings_frm.workplace.value)){
		alert("사업장 주소를 입력해주세요!");
	}else if(space.test(settings_frm.info_mng.value)){
		alert("정보관리책임자명을 입력해주세요!");
	}else if(space.test(settings_frm.info_mng_email.value)){
		alert("정보책임자 이메일을 입력해주세요!");
	}else if(settings_frm.non_bank.value!=""&&!KO.test(settings_frm.non_bank.value)){
			alert("무통장 은행명은 한글만 입력가능합니다!");
	}else if(settings_frm.non_bank.value!=""&&!num16.test(settings_frm.bank_num.value)){
			alert("은행계좌번호는 숫자와 -을 포함한 16자리까지만 입력할수 있습니다!");
	}else if(space.test(settings_frm.pay_low.value)){
		alert("결제 최소 포인트를 입력해주세요!");
	}else if(space.test(settings_frm.pay_hign.value)){
		alert("결제 최대 포인트를 입력해주세요!");
	}else if(settings_frm.pay_hign.value<settings_frm.pay_low.value){
		alert("결제 최소 포인트는 결제 최대 포인트보다 적으면 안됩니다!");
	}else if(space.test(settings_frm.deli_name.value)){
		alert("배송업체명을 입력해주세요!");
	}
	else if(space.test(settings_frm.deli_pay.value)){
		alert("배송비 입력해주세요!");	
	}else{
	settings_frm.method="post";
	settings_frm.action="./siteinfo_write";
	settings_frm.submit();
	}
}


function delete_write_info(){
if(confirm("정말로 저장을 취소하시겠습니까?")){
if(confirm("저장 취소 후 데이터를 복구 하실수 없습니다. 저장 취소 하시겠습니까?")){
location.href="./siteinfo";
}
}
}