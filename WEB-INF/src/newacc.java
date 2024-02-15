import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class newacc extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String ph=req.getParameter("ph");
		String nm=req.getParameter("nm");
		String aa=req.getParameter("aa");
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		String ta=req.getParameter("ta");
		String balance="0";
		int max=1,max_sal=1;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","12345");
			PreparedStatement pst=con.prepareStatement("Select * from newacc where phone_number=?");
			pst.setString(1,ph);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				pw.println("Already register");
			}
			else
			{
				PreparedStatement pst1=con.prepareStatement("select max(account_no) from newacc");
				ResultSet rs1=pst1.executeQuery();
				if(rs1.next())
				{
					pw.println(pst1+1);
					//PreparedStatement pst1=con.prepareStatement("insert into newacc values(?,?,?,?,?,?,?)");
					//pst1.setString(1,nm);
					//pst1.setString(2,ph);
					//pst1.setString(3,aa);
					//pst1.setString(4,email);
					//pst1.setString(5,pass);
					//pst1.setString(6,ta);
					//pst1.setString(7,balance);
					//pst1.setInt(8,max+1);
					//int t=pst1.executeUpdate();
					//if(t>0)
				    	    //pw.println("<h3>Registation successfull");
				}
				//else
				//{
					//PreparedStatement pst3=con.prepareStatement("insert into newacc values(?,?,?,?,?,?,?)");
					//pst3.setString(1,nm);
					//pst3.setString(2,ph);
					//pst3.setString(3,aa);
					//pst3.setString(4,email);
					//pst3.setString(5,pass);
					//pst3.setString(6,ta);
					//pst3.setString(7,balance);
					//pst3.setInt(8,max);
					//int t=pst3.executeUpdate();
					//if(t>0)
				    	   //pw.println("<h3>Registation successfull");
				//}
				
			}
			con.close();
		}
		catch(Exception e)
		{
			pw.println(e);
		}
	}
}