import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class sign extends HttpServlet
{
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter pw = res.getWriter();
    String nm = req.getParameter("nm");
    String pass = req.getParameter("pass");
    String ph = req.getParameter("ph");
    HttpSession ses = req.getSession();
    ses.setAttribute("name", nm);
    ses.setAttribute("phone", ph);
    try {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "12345");
        PreparedStatement pst1 = con.prepareStatement("select * from information where phone=? and password=?");
        pst1.setString(1, ph);
        pst1.setString(2, pass);
        ResultSet rs = pst1.executeQuery();
        if (rs.next()) {
            if (rs.getString(3).equals(ph) && rs.getString(2).equals(pass)) {
                pw.println("<!DOCTYPE html>");
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Train Ticket Booking System</title>");
                pw.println("<style>");
                pw.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; }");
                pw.println("center { padding: 20px; }");
                pw.println("a { text-decoration: none; color: #fff; background-color: #007bff; padding: 10px 20px; border-radius: 4px; margin: 5px; }");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<center>");
                pw.println("<h1>Welcome, " + nm + "!</h1>");
                pw.println("<h2>Choose an option:</h2>");
                pw.println("<a href=\"time.html\">Show Train Time</a>");
                pw.println("<a href=\"bookticket.html\">Book Ticket</a>");
                pw.println("<a href=\"showticket.html\">Show Ticket</a>");
                pw.println("</center>");
                pw.println("</body>");
                pw.println("</html>");
            }
        } else {
            pw.println("<h3>Incorrect phone number or password</h3>");
        }
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
