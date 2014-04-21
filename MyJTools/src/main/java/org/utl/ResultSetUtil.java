package org.utl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetUtil {
	public static ArrayList<String> getColumns(ResultSetMetaData rsmd)
	{
		ArrayList<String> ret = new ArrayList<String>();
		try {
			for (int i = 1; i <=rsmd.getColumnCount(); i++) {
				ret.add(rsmd.getColumnLabel(i));			
			}
			return ret;	
		} catch (SQLException e) {
			e.printStackTrace();
			return ret;
		}
	}
	
	public static int getResultSetRecordCount(ResultSet rs) throws SQLException
	{		
		int r = rs.getRow();
		rs.last();
		int ret = rs.getRow();
		rs.absolute(r);
		return ret; 
	}
}
