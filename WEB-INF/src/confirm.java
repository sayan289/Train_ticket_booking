import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.util.Calendar;
public class confirm extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession ses=req.getSession();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    		Date date = new Date(); 
    		String a=formatter.format(date);
		String nm=(String)ses.getAttribute("name");
		String ph=(String)ses.getAttribute("phone");
		String st=(String)ses.getAttribute("source");
		String dt=(String)ses.getAttribute("destination");
		ses.setAttribute("time",a);
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			PreparedStatement pst=con.prepareStatement("insert into payment values(?,?,?,?,?)");
			pst.setString(1,nm);
			pst.setString(2,ph);
			pst.setString(3,st);
			pst.setString(4,dt);
			pst.setString(5,a);
			int t=pst.executeUpdate();
			if(t>0)
			{
			   	pw.println("<h3>Payment Successfull");
				pw.println(a);
			}
			con.close();
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}