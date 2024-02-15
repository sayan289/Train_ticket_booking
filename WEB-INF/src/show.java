import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class show extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        HttpSession ses = req.getSession();
	int f=0;
        String ph = (String) ses.getAttribute("phone");
        String a = (String) ses.getAttribute("time");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "12345");
            PreparedStatement pst = con.prepareStatement("select * from payment where phone=? and time=?");
            pst.setString(1, ph);
            pst.setString(2, a);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                pw.println("<!DOCTYPE html>");
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Train Ticket Booking System</title>");
                pw.println("<style>");
                pw.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; margin: 0; }");
                pw.println(".ticket { background-color: #fff; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); border-radius: 8px; padding: 20px; max-width: 600px; margin: 50px auto; }");
                pw.println(".ticket h2 { color: #007bff; }");
                pw.println(".ticket table { width: 100%; }");
                pw.println(".ticket td { padding: 10px; font-size: 18px; color: #444; }");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<div class='ticket'>");
                pw.println("<h2>Train Ticket Details</h2>");
                pw.println("<table>");
                pw.println("<tr><td>Name:</td><td>" + rs.getString(1) + "</td></tr>");
                pw.println("<tr><td>Phone Number:</td><td>" + rs.getString(2) + "</td></tr>");
                pw.println("<tr><td>Source:</td><td>" + rs.getString(3) + "</td></tr>");
                pw.println("<tr><td>Destination:</td><td>" + rs.getString(4) + "</td></tr>");
                pw.println("<tr><td>Booking Date:</td><td>" + rs.getString(5) + "</td></tr>");
                pw.println("</table>");
                pw.println("</div>");
                pw.println("</body>");
                pw.println("</html>");
		f++;
            } if(f==0) {
                pw.println("<!DOCTYPE html>");
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Train Ticket Booking System</title>");
                pw.println("<style>");
                pw.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; }");
                pw.println("center { padding: 20px; }");
                pw.println("h3 { color: #ff0000; }");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<center>");
                pw.println("<h3>Book your Ticket First</h3>");
                pw.println("</center>");
                pw.println("</body>");
                pw.println("</html>");
            }

            con.close();
        } catch (Exception e) {
            pw.println(e);
        }
    }
}