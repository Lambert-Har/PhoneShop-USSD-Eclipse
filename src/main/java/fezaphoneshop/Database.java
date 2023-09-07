package fezaphoneshop;

import java.sql.*;

public class Database {

	public static Connection getConnection () throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/20rp06566_fezaphoneshop_db","root","");
	}
	
}
