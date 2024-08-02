package shopping_admin;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_cate_dao {
	int cidx;
	String classcode,lname,lcode,cuse;
	
	public ArrayList<Object> list (){
		ArrayList<Object> list=new ArrayList<Object>();
		list.add(getCidx());
		list.add(getClass());
		list.add(getLname());
		list.add(getLcode());
		list.add(getCuse());
		return list;
	}
	
}
