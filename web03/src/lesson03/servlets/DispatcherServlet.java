package lesson03.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson03.controls.Controller;
import lesson03.controls.MemberAddController;
import lesson03.controls.MemberListController;
import lesson03.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void service( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try{			
			ServletContext sc = this.getServletContext();
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("memberDao", sc.getAttribute("memberDao"));
			
			String pageControllerPath = null;
			Controller pageController = null;
			
			if("/member/list.do".equals(servletPath)) {
				//pageControllerPath = "/member/list";
				pageController = new MemberListController();
			} else if ("/member/add.do".equals(servletPath)){
				//pageControllerPath = "/member/add";
				pageController = new MemberAddController();
				if (request.getParameter("email") != null) {
					//request.setAttribute("member",  new Member()
					model.put("member",  new Member()
						   .setEmail(request.getParameter("email"))
						   .setPassword(request.getParameter("password"))
						   .setName(request.getParameter("name"))
							);
				}
			}
			
			String viewUrl = pageController.execute(model);
			
			for (String key : model.keySet()) {
				request.setAttribute(key,  model.get(key));
			}				
			
			//RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			//rd.include(request,  response);
			//String viewUrl = (String) request.getAttribute("viewUrl");
			if (viewUrl.startsWith("redirect:")){
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request,  response);
		}
		
	}
	

}
