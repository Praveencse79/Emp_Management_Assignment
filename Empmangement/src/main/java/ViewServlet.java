

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='register.html'>Add New Employee</a>");
		out.println("<h1>Employees List</h1>");
		
		List<Employee> list=EmpDao.getAllEmployees();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Employee Id</th><th>Name</th><th>DOB</th><th>Address</th><th>Gender</th><th>Edit</th><th>Delete</th></tr>");
		for(Employee e:list){
			 //Declaring a variable for switch expression  
		    int number=e.getGender(); 
		    String gender=null;
		    //Switch expression  
		    switch(number){  
		    //Case statements  
		    case 1: gender="Male";  
		    break;  
		    case 2: gender="Female";  
		    break;  
		    //Default case statement  
		    default: gender="Other" ;  
		    }  
			out.print("<tr><td>"+e.getEmployeeId()+"</td><td>"+e.getName()+"</td><td>"+e.getBirthDate()+"</td><td>"+e.getAddress()+"</td><td>"+gender+"</td><td><a href='EditServlet?id="+e.getEmployeeId()+"'>edit</a></td><td><a href='ConfirmDialogServlet?id="+e.getEmployeeId()+"'>delete</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}
