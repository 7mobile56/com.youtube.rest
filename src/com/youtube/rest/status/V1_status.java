package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;
import com.youtube.dao.*;

// this is the root path of the restful api
@Path("/v1/status/")
public class V1_status {
	
	private static final String api_version= "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	// this is one down the root path of the restful api
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:" + api_version +"</p>";
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		//DATE_FORMAT(NOW(),'%W, %M %e, %Y @ %h:%i %p')
		try {
			conn = MySQLmdrabbani.MySQLmdrabbaniConn().getConnection();
			//query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			query = conn.prepareStatement("select sysdate() DATETIME");
			//query = conn.prepareStatement("select DATE_FORMAT(NOW(),'%%W, %%M %%e, %%Y @ %%h:%%i %%p')");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			
			returnString = "<p>Database Status</p>" +
					"<p>Database Date/Time return: " +
					myString + "</p";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) {
				conn.close();
			}
		}
		return returnString;
	}
}
