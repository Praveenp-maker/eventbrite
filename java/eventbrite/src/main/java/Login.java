import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jdbcURL = "jdbc:mysql://localhost:3306/eventsdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        try (Connection con = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword)) {
             String username=req.getParameter("username");
             String password=req.getParameter("password");
             System.out.println(username);
             System.out.println(password);
             String qry="select username,password from login where username=? AND password=?";
               PreparedStatement pstmt=con.prepareStatement(qry);
               pstmt.setString(1, username);
               pstmt.setString(2, password);
               ResultSet h=pstmt.executeQuery();
               boolean s=h.next();
               System.out.println(s);
             if(s==true){
                resp.sendRedirect("usertype.html");
             }
else{
    resp.sendRedirect("login.html");
}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}