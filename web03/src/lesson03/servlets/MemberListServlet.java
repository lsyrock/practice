package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/studydb?autoReconnect=true&useSSL=false",
						"test",
						"123123");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
						"select mno, mname, email, cre_date from members");
				
				//response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>회원목록</title></head>");
				out.println("<body><h1>회원목록</h1>");
				out.println("<p><a href='add'>신규 회원 생성</a></p>");
				while(rs.next()){
					System.out.println(rs.getString("MNAME"));
					out.println(
							rs.getString("MNO") + "," +
						    "<a href='update?no=" + rs.getInt("MNO") + "'>" +
							rs.getString("MNAME") + "</a>," +
							rs.getString("EMAIL") + "," +
							rs.getDate("CRE_DATE") + "<br>");
				}
				out.println("</body></html>");
				
			} catch (Exception e){
				throw new ServletException(e);
			} finally {
				try {if (rs != null) rs.close();} catch (Exception e) {}
				try {if (rs != null) stmt.close();} catch (Exception e) {}
				try {if (rs != null) conn.close();} catch (Exception e) {}
			}
			

	}

}
