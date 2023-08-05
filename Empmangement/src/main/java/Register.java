import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class Register extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
//String e=request.getParameter("employeeId");  
String n=request.getParameter("name");  
String a=request.getParameter("address");  
String g=request.getParameter("gender");  
String s=request.getParameter("salary");
String b=request.getParameter("birthday");
out.print("name:  "+n);         
try{  
Class.forName("oracle.jdbc.driver.OracleDriver");  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","student","123456");  
  
PreparedStatement ps=con.prepareStatement("insert into registeruser values(?,?,?,?,?)");  
  
//ps.setString(1,e);  
ps.setString(2,n);  
ps.setString(3,a);  
ps.setString(4,g);  
ps.setString(4,s);  
ps.setString(4,b);  

int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered...");  
      
          
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
  
}  