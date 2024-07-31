package shopping_admin;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_admin_dao {
	int sidx,master;
	String sid,spass,sname,semail,shp,spart,sdate;
	
	
	protected ArrayList<Object> lists(){
		ArrayList<Object> lists= new ArrayList<Object>();
		lists.add(getSid());
		lists.add(getSpass());
		lists.add(getSname());
		lists.add(getMaster());
		return lists;
	}
	
}
