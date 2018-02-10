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

import lesson03.bind.DataBinding;
import lesson03.bind.ServletRequestDataBinder;
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
			//model.put("memberDao", sc.getAttribute("memberDao"));
			model.put("session",  request.getSession());
			
			Controller pageController = (Controller) sc.getAttribute(servletPath);
			
			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);
			
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if (viewUrl.startsWith("redirect:")){
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}		
			
			
			
			//String pageControllerPath = null;
			//Controller pageController = null;
			
			//if("/member/list.do".equals(servletPath)) {
				//pageControllerPath = "/member/list";
				//pageController = new MemberListController();
			//} else if ("/member/add.do".equals(servletPath)){
				//pageControllerPath = "/member/add";
				//pageController = new MemberAddController();
			//	if (request.getParameter("email") != null) {
					//request.setAttribute("member",  new Member()
			//		model.put("member",  new Member()
			//			   .setEmail(request.getParameter("email"))
			//			   .setPassword(request.getParameter("password"))
			//			   .setName(request.getParameter("name"))
			//				);
			//	}
			//}
			
			
			//for (String key : model.keySet()) {
			//	request.setAttribute(key,  model.get(key));
			//}				
			
			//RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			//rd.include(request,  response);
			//String viewUrl = (String) request.getAttribute("viewUrl");
			//if (viewUrl.startsWith("redirect:")){
			//	response.sendRedirect(viewUrl.substring(9));
			//	return;
			//} else {
			//	RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
			//	rd.include(request, response);
			//}			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request,  response);
		}
		
	}
	
	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, DataBinding dataBinding)
		throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		 
		for (int i = 0; i < dataBinders.length; i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
}
