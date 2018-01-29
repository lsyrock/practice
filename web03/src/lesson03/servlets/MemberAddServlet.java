package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson03.dao.MemberDao;
import lesson03.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException{
		
		request.setAttribute("viewUrl",  "/member/MemberForm.jsp");
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException{
		
		//request.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/studydb?autoReconnect=true&useSSL=false",
					"test",
					"123123");
			stmt = conn.prepareStatement("insert into members(email, pwd, mname, cre_date, mod_date)"
					+ "values(?,?,?,now(),now()) ");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
			stmt.executeUpdate();
			
			//response.sendRedirect("list");
			request.setAttribute("viewUrl", "redirect:list.do");
			
			//리다이렉 이후에 소스코드는 실행되지 않는다.
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원등록결과!!</title></head>");
			out.println("<meta http-equiv='Refresh' content='50; url=list'>");
			out.println("<body>");
			out.println("<p>등록 성공입니다!</p>");
			out.println("</body></html>");
			
			//리프래시 정보를 응답 헤더에 추가
			//response.addHeader("Refresh", "50;url=list");
			
			
		} catch(Exception e){
			throw new ServletException(e);
		} finally{
			try{ if(stmt != null) stmt.close();} catch(Exception e) {}
			try{ if(conn != null) stmt.close();} catch(Exception e) {}
		}
	}
	
	
	
}
