package lesson03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tools.ant.Project;

import lesson03.annotation.Component;
import lesson03.util.DBConnectionPool;
import lesson03.vo.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao{
	
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Project> selectList(HashMap<String, Object> paramMap)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			return sqlSession.selectList("lesson03.dao.MemberDao.selectList");
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		try{
			int count = sqlSession.insert("lesson03.dao.MemberDao.insert", project);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	 //Connection connection;
	 
	 //public void setConnection(Connection connection){
	 //	 this.connection = connection;
	 //}
	 
	//DBConnectionPool connPool;
	
	//DataSource ds;
	
	//public void setDbConnectionPool(DBConnectionPool connPool) {
	//	this.connPool = connPool;
	//}
	//public void setDataSource(DataSource ds) {
	//	this.ds = ds;
	//}
	
	 //public List<Member> selectList() throws Exception {
	 //	Connection connection = null;
	//	Statement stmt = null;
		//ResultSet rs = null;
			
		//try{
			
			//connection = connPool.getConnection();
			//connection = ds.getConnection();
			//stmt = connection.createStatement();
			//rs = stmt.executeQuery(
					//"select mno, mname, email, cre_date from members order by mno asc");
			
			//ArrayList<Member> members = new ArrayList<Member>();
			
			//while (rs.next()){
				//members.add(new Member().setNo(rs.getInt("MNO"))
					//				    .setName(rs.getString("MNAME"))
						//			    .setEmail(rs.getString("EMAIL"))
							//		    .setCreateDate(rs.getDate("CRE_DATE"))
							//);
			//}
			//return members;
		//} catch (Exception e) {
			//throw e;
		//}finally{
			//try{ if (rs != null) rs.close();} catch (Exception e){}
			//try{ if (stmt != null) rs.close();} catch (Exception e){}
			//if(connection != null) connPool.returnConnection(connection);
			//try{ if(connection != null) connection.close();} catch(Exception e){}
		//}	 
	 //}

	 //public int insert(Member member) throws Exception  {
		//    PreparedStatement stmt = null;
		  //  Connection connection = null;
	//	    try {
	//	      connection = connPool.getConnection();
	//	      stmt = connection.prepareStatement(
	//	          "INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
	//	              + " VALUES (?,?,?,NOW(),NOW())");
	//	      stmt.setString(1, member.getEmail());
	//	      stmt.setString(2, member.getPassword());
	///	      stmt.setString(3, member.getName());
	//	      return stmt.executeUpdate();

	//	    } catch (Exception e) {
	//	      throw e;

	//	    } finally {
	//	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	//	    }
	//	  }

}