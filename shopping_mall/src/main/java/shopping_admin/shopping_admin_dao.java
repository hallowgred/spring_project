package shopping_admin;

import java.util.ArrayList;

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
	
	protected ArrayList<ArrayList<Object>> dou_list(){
		ArrayList<ArrayList<Object>> all = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(getSidx());
		al.add(getSpass());
		al.add(getSname());
		al.add(getSemail());
		al.add(getShp());
		String[] sp= getSpart().split("!");
		al.add(sp[0]);
		al.add(sp[1]);
		al.add(master);
		al.add(getSdate());
		all.add(al);
		return all;
	}
}
