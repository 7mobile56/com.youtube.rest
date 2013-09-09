package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

public class MySQLmdrabbani {

	private static DataSource MySQLmdrabbani = null;
	private static Context context = null;
	
	public static DataSource MySQLmdrabbaniConn() throws Exception {
		
		if (MySQLmdrabbani != null) {
			return MySQLmdrabbani;
		}
		try{
			if (context == null) {
				context = new InitialContext();
			}
			
			MySQLmdrabbani = (DataSource) context.lookup("mdrabbaniMySQL");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return MySQLmdrabbani;
	}
}
