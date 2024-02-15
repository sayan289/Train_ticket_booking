import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class regis1 extends HttpServlet
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
			PreparedStatement pst1=con.prepareStatement("select * from information where phone=?");
			pst1.setString(1,ph);
			ResultSet rs=pst1.executeQuery();
			if(rs.next())
			     pw.println("phone number already exists");
			else
			{
				PreparedStatement pst=con.prepareStatement("insert into information values(?,?,?)");
				pst.setString(1,nm);
				pst.setString(2,pass);
				pst.setString(3,ph);
				int t=pst.executeUpdate();
				if(t>0)
			   	      pw.println("<h3>Register successfull");
				con.close();
			}
			
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}