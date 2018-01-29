package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson03.dao.MemberDao;
import lesson03.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//Connection conn = null;
			//Statement stmt = null;
			//ResultSet rs = null;
			
			try {
				ServletContext sc = this.getServletContext();
				//Class.forName(sc.getInitParameter("driver"));
				//conn = DriverManager.getConnection( sc.getInitParameter("url"),
				//									sc.getInitParameter("username"),
				//									sc.getInitParameter("password"));
				
				
				//stmt = conn.createStatement();
				//rs = stmt.executeQuery(
				//		"select mno, mname, email, cre_date from members order by mno asc");
				
				response.setContentType("text/html charset=UTF-8");
				
				//ArrayList<Member> members = new ArrayList<Member>();
				//데이터베이스에서 회워 정보를 가져와 Member에 담는다
				//그리고 Member객체를 ArrayList에 추가한다. 
				//while(rs.next()){
				//	members.add(new Member().setNo(rs.getInt("MNO"))
				//			                .setName(rs.getString("MNAME"))
				//			                .setEmail(rs.getString("EMAIL"))
				//			                .setCreateDate(rs.getDate("CRE_DATE"))
				//			);
				//}
				
				//request에 회원 목록 데이터를 보관한다 .
				//request.setAttribute("members", members);
				
				//conn = (Connection) sc.getAttribute("conn");
				
				//MemberDao memberDao = new MemberDao();
				MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
				//memberDao.setConnection(conn);
				
				request.setAttribute("members", memberDao.selectList());
				request.setAttribute("viewUrl",  "/member/MemberList.jsp");
				//JSP로 출력을 위임한다. 
				//RequestDispatcher rd = request.getRequestDispatcher( "/member/MemberList.jsp");
				//System.out.println("jsp 위임전");
				//rd.forward(request, response);
				//rd.include(request, response);
				//System.out.println("jsp 위임 완료 ");
				
			} catch (Exception e){
				throw new ServletException(e);
			} finally {
				//try {if (rs != null) rs.close();} catch (Exception e) {}
				//try {if (rs != null) stmt.close();} catch (Exception e) {}
				//try {if (rs != null) conn.close();} catch (Exception e) {}
			}
			

	}

}
