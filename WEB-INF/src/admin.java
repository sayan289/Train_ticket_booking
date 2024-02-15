import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class admin extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String id=req.getParameter("id");
		String st=req.getParameter("st");
		String path=req.getParameter("path");
		String ds=req.getParameter("ds");
		String time=req.getParameter("time");
		String km=req.getParameter("km");
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			PreparedStatement pst=con.prepareStatement("insert into train_info values(?,?,?,?,?,?)");
			pst.setString(1,id);
			pst.setString(2,st);
			pst.setString(3,path);
			pst.setString(4,ds);
			pst.setString(5,time);
			pst.setString(6,km);
			int t=pst.executeUpdate();
			if(t>0)
			    pw.println("<h3>Train information submitted  successfully");
			con.close();
			
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}