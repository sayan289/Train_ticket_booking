import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class authority_validate extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String nm = req.getParameter("nm");
        String pass = req.getParameter("pass");
        String ph = req.getParameter("ph");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "12345");
            PreparedStatement pst = con.prepareStatement("select * from authority where phone_no=? and password=?");
            pst.setString(1, ph);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            
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
            
            if (rs.next()) {
                if (rs.getString(3).equals(ph) && rs.getString(2).equals(pass)) {
                    pw.println("<a href=\"addtrain.html\">Add Train Details</a>");
                    pw.println("<a href=\"deletetrain.html\">Delete Train Details</a>");
                    pw.println("<a href=\"insertfare.html\">Insert Fare</a>");
                }
            } else {
                pw.println("<h3>Wrong phone number or password</h3>");
            }
            
            pw.println("</body>");
            pw.println("</html>");
            
            con.close();
        } catch (Exception e) {
            pw.println(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}