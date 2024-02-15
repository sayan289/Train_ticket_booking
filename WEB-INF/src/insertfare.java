import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class insertfare extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String km=req.getParameter("km");
		String fr=req.getParameter("fr");
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			PreparedStatement pst=con.prepareStatement("insert into fare_info values(?,?)");
			pst.setString(1,km);
			pst.setString(2,fr);
			int t=pst.executeUpdate();
			if(t>0)
			    pw.println("<h3>Fare Insert Successfull");
			con.close();
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}