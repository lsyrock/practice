package lesson03.listener;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;









import javax.naming.InitialContext;
//import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;






import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import lesson03.context.ApplicationContext;
import lesson03.dao.MemberDao;
import lesson03.util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	//Connection conn;
	//DBConnectionPool connPool;
	//BasicDataSource ds;
	
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try{
			applicationContext = new ApplicationContext();
			
			String resource = "lesson03/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			
			ServletContext sc = event.getServletContext();
			String propertiesPath = sc.getRealPath( sc.getInitParameter("contextConfigLocation"));
			
			applicationContext.prepareObjectsByProperties(propertiesPath);
			
			applicationContext.prepareObjectByAnnotation("");
			
			applicationContext.injectDependency();	
			
			//ServletContext sc = event.getServletContext();
			//
			//String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			//applicationContext = new ApplicationContext(propertiesPath);			
			
			
			//ServletContext sc = event.getServletContext();
			
			//Class.forName(sc.getInitParameter("driver"));
			//conn = DriverManager.getConnection(
			//		sc.getInitParameter("url"),
			//		sc.getInitParameter("username"),
			//		sc.getInitParameter("password")					
			//);
			
			//connPool = new DBConnectionPool(
			//		sc.getInitParameter("driver"),
			//		sc.getInitParameter("url"),
			//		sc.getInitParameter("username"),
			//		sc.getInitParameter("password")
			//		);

			//ds = new BasicDataSource();
			//ds.setDriverClassName(sc.getInitParameter("driver"));
			//ds.setUrl(sc.getInitParameter("url"));
			//ds.setUsername(sc.getInitParameter("username"));
			//ds.setPassword(sc.getInitParameter("password"));
			
			//ServletContext sc = event.getServletContext();
			
			//InitialContext initialContext = new InitialContext();
			//DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");
			
			//MemberDao memberDao = new MemberDao();
			//memberDao.setDataSource(ds);
			//memberDao.setConnection(conn);
			//memberDao.setDbConnectionPool(connPool);
			
			//sc.setAttribute("memberDao", memberDao);
	
		} catch(Throwable e) {
			e.printStackTrace();
		}		
	}
		
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//try{
		//	conn.close();			
		//} catch (Exception e ){}
		
		//connPool.closeAll();
		
	//	try{ if (ds != null) ds.close(); } catch (SQLException e) {}	
	}
}