import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class new_authority extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String nm=req.getParameter("nm");
		String pass=req.getParameter("pass");
		String ph=req.getParameter("ph");
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			PreparedStatement pst=con.prepareStatement("insert into authority values(?,?,?)");
			pst.setString(1,nm);
			pst.setString(2,pass);
			pst.setString(3,ph);
			int t=pst.executeUpdate();
			if(t>0)
			   pw.println("<h3>Inserted successfully");
			con.close();

		}
		catch(Exception e){}
		
	}
}