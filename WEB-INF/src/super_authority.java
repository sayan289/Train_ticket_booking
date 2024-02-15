import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class super_authority extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String nm=req.getParameter("nm");
		String pass=req.getParameter("pass");
		String a="admin123";
		String b="admin";
		pw.println("<html>");
            	pw.println("<head>");
            	pw.println("<title>Authority Dashboard</title>");
            	pw.println("<style>");
            	pw.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 50vh; margin: 0; }");
            	pw.println("a { text-decoration: none; background-color: #ff8c00; color: #fff; padding: 10px 20px; border-radius: 4px; margin: 5px; display: block; width: 200px; text-align: center; }");
            	pw.println("h3 { color: red; text-align: center; }");
            	pw.println("</style>");
            	pw.println("</head>");
            	pw.println("<body>");
		if(nm.equals(a) && pass.equals(b))
		{
			pw.println("<a href=\"new_authority.html\">Add new Authority details</a><br><br>");
		}
		else
		{
			pw.println("<h3>wrong username or password");
		}
		
	}
}