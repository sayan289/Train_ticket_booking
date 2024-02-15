import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class time extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        int f = 0, in = 0, i = 0, j = 0;
        PrintWriter pw = res.getWriter();
        String st = req.getParameter("st");
        String dt = req.getParameter("dt");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "12345");
            PreparedStatement pst = con.prepareStatement("select * from train_info order by time asc");
            ResultSet rs = pst.executeQuery();
            
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Train Search Results</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; }");
            pw.println("table { border-collapse: collapse; width: 100%; }");
            pw.println("th, td { text-align: left; padding: 8px; }");
            pw.println("th { background-color: #f2f2f2; }");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");
            
            pw.println("<h1>Train Search Results</h1>");
            pw.println("<table>");
            pw.println("<tr><th>Train ID</th><th>Source</th><th>Destination</th><th>Time</th></tr>");

            while (rs.next()) {
                String s1 = rs.getString(3);
                String s[] = s1.split(",");
                for (i = 0; i < s.length; i++) {
                    if (s[i].equals(st)) {
                        for (j = i+1; j < s.length; j++) {
                            if (s[j].equals(dt)) {
                                pw.println("<tr>");
				f++;
                                pw.println("<td>" + rs.getString(1) + "</td>");
                                pw.println("<td>" + rs.getString(2) + "</td>");
                                pw.println("<td>" + rs.getString(4) + "</td>");
                                pw.println("<td>" + rs.getString(5) + "</td>");
                                pw.println("</tr>");
                            }
                        }
                    }
                }
            }
           
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");
            if(f==0)
		pw.println("No Train Found");
            con.close();
        } catch (Exception e) {
            pw.println(e);
        }
    }
}