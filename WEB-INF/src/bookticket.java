import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class bookticket extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int i, j = 0, k, l, in = 0, in1 = 0, f = 0, diff;
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String st = req.getParameter("st");
        String dt = req.getParameter("dt");
        HttpSession ses = req.getSession();
        ses.setAttribute("source", st);
        ses.setAttribute("destination", dt);
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "12345");
            PreparedStatement pst = con.prepareStatement("select * from train_info");
            ResultSet rs = pst.executeQuery();
            f = 0;
            while (rs.next()) {
                if (f == 1)
                    break;
                String s1 = rs.getString(3);
                String s2 = rs.getString(6);
                String s[] = s1.split(",");
                String s4[] = s2.split(",");
                for (i = 0; i < s.length; i++) {
                    if (f == 1)
                        break;
                    else if (s[i].equals(st)) {
                        for (j = i; j < s.length; j++) {
                            if (s[j].equals(dt)) {
                                in1 = Integer.parseInt(s4[j]);
                                in = Integer.parseInt(s4[i]);
                                f = 1;
                                break;
                            }
                        }

                    }
                }

            }
            diff = Math.abs(in1 - in);
            String diff1 = String.valueOf(diff);
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Train Ticket Booking System</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; }");
            pw.println("center { padding: 20px; }");
            pw.println("h3 { color: #007bff; }");
            pw.println("form { margin-top: 20px; }");
            pw.println("input[type='submit'] { background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");
            pw.println("<h2>Train Ticket Booking</h2>");
            pw.println("<h3>Total KM: " + diff + "</h3>");
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "12345");
            PreparedStatement pst1 = con1.prepareStatement("select fare from fare_info where km=?");
            pst1.setString(1, diff1);
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                pw.println("<h3>Fare: " + rs1.getString(1) + "</h3>");
                pw.println("<form action=\"confirm\" method=\"get\">");
                pw.println("<input type=\"submit\" value=\"Make Payment\">");
                pw.println("</form>");
            }
            pw.println("</center>");
            pw.println("</body>");
            pw.println("</html>");
            con.close();
            con1.close();
        } catch (Exception e) {
            pw.println(e);
        }
    }
}
