package shopping_admin;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class shopping_settings_dao {
	int hidx,hsignlev,hsignpoint,pay_low,pay_hign,deli_pay;
	String hname,hemail,hpoint,corname,business_reg,repname,rephp,business_mail_order,add_commu
	,workplace_mail,workplace,info_mng
	,info_mng_email,non_bank,bank_num
	,Credit_card,mobile_pay,book_gift
	,cash_rec,deli_name,hope_day
	;
	
	ArrayList<Object> list(){
		ArrayList<Object> list=new ArrayList<Object>();
		list.add(getHidx());
		list.add(getHname());
		list.add(getHemail());
		list.add(getHpoint());
		list.add(getHsignpoint());
		list.add(getHsignlev());
		list.add(getCorname());
		list.add(getBusiness_reg());
		list.add(getRepname());
		list.add(getRephp());
		list.add(getBusiness_mail_order());
		list.add(getAdd_commu());
		list.add(getWorkplace_mail());
		list.add(getWorkplace());
		list.add(getInfo_mng());
		list.add(getInfo_mng_email());
		list.add(getNon_bank());
		list.add(getBank_num());
		list.add(getCredit_card());
		list.add(getMobile_pay());
		list.add(getBook_gift());
		list.add(getPay_low());
		list.add(getPay_hign());
		list.add(getCash_rec());
		list.add(getDeli_name());
		list.add(getDeli_pay());
		list.add(getHope_day());
		return list;
	}
	
}
