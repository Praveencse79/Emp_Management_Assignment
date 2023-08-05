

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try {
			String name=request.getParameter("name");
			String address=request.getParameter("address");	
			Byte gender=Byte.valueOf(request.getParameter("gender"));
			Double salary=Double.valueOf(request.getParameter("salary"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date birthday = formatter.parse(request.getParameter("birthdate"));
			System.out.println("birthday "+birthday);
			Employee e=new Employee();
			e.setName(name);
			e.setAddress(address);
			e.setGender(gender);
			e.setSalary(salary);
			e.setBirthDate(birthday);

			int status=EmpDao.save(e);
			if(status>0){
				out.print("<p>Record saved successfully!</p>");
				request.getRequestDispatcher("register.html").include(request, response);
			}else{
				out.println("Sorry! unable to save record");
			}
			
			out.close();  
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}
