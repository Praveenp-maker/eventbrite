 import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {
    ArrayList<Eventmodel> listBook = new ArrayList<Eventmodel>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listBook.clear();
        System.out.println("get method called after table");
        String jdbcURL = "jdbc:mysql://localhost:3306/eventsdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        String qry="Select * from events";
       //ArrayList<Eventmodel> listBook = new ArrayList<Eventmodel>();
        ResultSet rs=null;
        PreparedStatement ps=null;
try{
    Connection con=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
  ps=con.prepareStatement(qry);
 rs= ps.executeQuery();
while(rs.next()){
        int event_id=rs.getInt("event_id");
    String event_name=rs.getString("event_name");
    String description=rs.getString("description");
   String location= rs.getString("location");
   String date=rs.getString("date");
   String time=rs.getString("time");
   String venue=rs.getString("venue");
   String concept=rs.getString("concept");
   String company=rs.getString("company");
   int prize=rs.getInt("prize");
  listBook.add(new Eventmodel(event_id, event_name, description, location, date, time, venue, concept, company,prize));
  System.out.println(event_id+" "+description+" "+event_name+" "+location+""+date+" "+time+" "+venue+" "+concept+" "+company+" "+prize);
}
 // req.setAttribute("listBook", listBook);
 // resp.sendRedirect("viewer.jsp");
  //RequestDispatcher dispatcher = req.getRequestDispatcher("viewer.jsp");
  //dispatcher.forward(req, resp);
}
catch(SQLException e){
e.printStackTrace();
}
Gson g = new Gson();
String books = g.toJson(listBook);
resp.getWriter().print(books);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter the  s1 post method");
        try(Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/eventsdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "")){
        //PreparedStatement pstmt = conn.createStatement();
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Gson g = new Gson();
        Eventmodel newBook = g.fromJson(requestData, Eventmodel.class);
         PreparedStatement pstmt = conn.prepareStatement("insert into events values(?,?,?,?,?,?,?,?,?,?)");
                // String id = newBook.getId();
                // String name = req.getParameter("name");
                // String depth = req.getParameter("depth");
                System.out.println(newBook.getDate());
                 pstmt.setInt(1, newBook.getEvent_id());
                 pstmt.setString(2, newBook.getEvent_name());
                 pstmt.setString(3, newBook.getDescription());
                 pstmt.setString(4, newBook.getLocation());
                 pstmt.setString(5, newBook.getDate());
                 pstmt.setString(6, newBook.getTime());
                 pstmt.setString(7, newBook.getVenue());
                 pstmt.setString(8, newBook.getConcept());
                 pstmt.setString(9, newBook.getCompany());
                pstmt.setInt(10, newBook.getPrize());
               int status = pstmt.executeUpdate();
        //System.out.println(newBook.getId() + " " + newBook.getName() + " " + newBook.getDept());
                 System.out.println("insert succesfully");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
 @Override
 protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     System.out.println("hello");
     ArrayList<Eventmodel> listBook1 = new ArrayList<Eventmodel>();  
     String requestData = req.getReader().lines().collect(Collectors.joining());
     Gson g = new Gson();
     Eventmodel newBook = g.fromJson(requestData, Eventmodel.class);
     int a=newBook.getEvent_id();
     System.out.println(a);
     //int b=req.getParameter(requestData);
     try(Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/eventsdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "")){
        //PreparedStatement pstmt = conn.createStatement()
                // String id = req.getParameter("id");
                // String name = req.getParameter("name");
                // String dept = req.getParameter("dept");
                // System.out.println(id);
                // System.out.println(name);
                // System.out.println(dept);
              //  System.out.println(a);
                PreparedStatement pstmt = conn.prepareStatement("select * from events where event_id = ?");
              pstmt.setInt(1,a);
         ResultSet status = pstmt.executeQuery();
             ResultSet rset = status;
            System.out.println("The records selected are:");
            int rowCount = 0;
            while (rset.next()) { // Repeatedly process each row
                int event_id = rset.getInt("event_id");
                String  event_name= rset.getString("event_name");
                String description = rset.getString("description");
                String location = rset.getString("location");
                String date = rset.getString("date");
                String time = rset.getString("time");
                String venue= rset.getString("venue");
                String concept = rset.getString("concept");
                String company = rset.getString("company");
                 int prize= rset.getInt("prize");
                System.out.println(event_id + ", " + event_name + ", " + description);
                ++rowCount;
                //booksList.add(new Book(id, name, dept));
                listBook1.add(new Eventmodel(event_id, event_name, description, location, date, time, venue, concept, company,prize));
            }
   System.out.println(listBook1);
            // RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
            //  req.setAttribute("bookList", listBook1);
            // rd.forward(req,resp);
           // resp.sendRedirect("order.jsp");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    Gson j = new Gson();
 String books = j.toJson(listBook1);
 resp.getWriter().print(books);
 }}