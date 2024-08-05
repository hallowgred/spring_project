package pay;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbinfo {

	public Connection info() throws Exception{
		String driver="com.mysql.cj.jdbc.Driver";
		String db_url ="jdbc:mysql://localhost:3306/cms";
		String user="hana";
		String pass="hana1234";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(db_url,user,pass);
		return con;
	}
}
