import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class deletetrain extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String id=req.getParameter("id");
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			PreparedStatement pst=con.prepareStatement("delete from train_info where train_id=?");
			pst.setString(1,id);
			int t=pst.executeUpdate();
			if(t>0)
			    pw.println("<h3>Train information deleted  successfully");
			con.close();
			
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}