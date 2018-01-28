package lesson03.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import lesson03.util.DBConnectionPool;
import lesson03.vo.Member;

public class MemberDao {
	 //Connection connection;
	 
	 //public void setConnection(Connection connection){
	 //	 this.connection = connection;
	 //}
	 
	//DBConnectionPool connPool;
	
	DataSource ds;
	
	//public void setDbConnectionPool(DBConnectionPool connPool) {
	//	this.connPool = connPool;
	//}
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	 public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
			
		try{
			
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select mno, mname, email, cre_date from members order by mno asc");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()){
				members.add(new Member().setNo(rs.getInt("MNO"))
									    .setName(rs.getString("MNAME"))
									    .setEmail(rs.getString("EMAIL"))
									    .setCreateDate(rs.getDate("CRE_DATE"))
							);
			}
			return members;
		} catch (Exception e) {
			throw e;
		}finally{
			try{ if (rs != null) rs.close();} catch (Exception e){}
			try{ if (stmt != null) rs.close();} catch (Exception e){}
			//if(connection != null) connPool.returnConnection(connection);
			try{ if(connection != null) connection.close();} catch(Exception e){}
		}	 
	 }
}