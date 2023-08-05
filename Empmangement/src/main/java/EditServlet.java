

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		Employee e=EmpDao.getEmployeeById(id);
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "    <title>Update employee information</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            max-width: 400px;\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            padding: 20px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        h1 {\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        form {\r\n"
				+ "            display: flex;\r\n"
				+ "            flex-direction: column;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        label {\r\n"
				+ "            font-weight: bold;\r\n"
				+ "            margin-bottom: 5px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        input[type=\"text\"],\r\n"
				+ "        input[type=\"date\"],\r\n"
				+ "        textarea,\r\n"
				+ "        input[type=\"number\"] {\r\n"
				+ "            padding: 8px;\r\n"
				+ "            margin-bottom: 10px;\r\n"
				+ "            border: 1px solid #ccc;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            width: 100%;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .gender-section {\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "            margin-bottom: 10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .gender-label {\r\n"
				+ "            margin-right: 10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        input[type=\"radio\"] {\r\n"
				+ "            margin-right: 5px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        input[type=\"submit\"] {\r\n"
				+ "            padding: 10px;\r\n"
				+ "            background-color: #4CAF50;\r\n"
				+ "            color: white;\r\n"
				+ "            border: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            cursor: pointer;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        input[type=\"submit\"]:hover {\r\n"
				+ "            background-color: #45a049;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .char-counter {\r\n"
				+ "            font-size: 12px;\r\n"
				+ "            color: #666;\r\n"
				+ "            margin-top: 5px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .error {\r\n"
				+ "            color: red;\r\n"
				+ "            margin-top: 5px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h2>  Update Employee Information</h2>\r\n"
				+ "    <form id=\"employeeForm\" action=\"EditServlet2\" method=\"post\">\r\n"
				+ "        <input type='hidden' name='id' value='"+e.getEmployeeId()+"'/>"
				+ "        <label for=\"name\">Name:</label>\r\n"
				+ "        <input type=\"text\" id=\"name\" name=\"name\" value="+ e.getName()+" required>\r\n"
				+ "\r\n"
				+ "        <label for=\"birthdate\">Birth Date:</label>\r\n"
				+ "        <input type=\"date\" id=\"birthdate\" name=\"birthdate\" value="+ e.getBirthDate()+" required>\r\n"
				+ "\r\n"
				+ "        <label for=\"address\">Address (Max 150 characters):</label>\r\n"
				+ "        <textarea id=\"address\" name=\"address\" rows=\"4\" cols=\"50\" value="+e.getAddress()+" required maxlength=\"150\"></textarea>\r\n"
				+ "\r\n"
				+ "        <div class=\"gender-section\">\r\n"
				+ "            <span class=\"gender-label\">Gender:</span>\r\n"
				+ "            <input type=\"radio\" id=\"male\" name=\"gender\" value=\"1\" required>\r\n"
				+ "            <label for=\"male\">Male</label>\r\n"
				+ "            <input type=\"radio\" id=\"female\" name=\"gender\" value=\"2\" required>\r\n"
				+ "            <label for=\"female\">Female</label>\r\n"
				+ "        </div>\r\n"
				+ "\r\n"
				+ "        <label for=\"salary\">Salary (Max 5 digits, Numbers only):</label>\r\n"
				+ "        <input type=\"text\" id=\"salary\" name=\"salary\" oninput=\"limitAndValidateSalary()\" value="+ e.getSalary()+" required>\r\n"
				+ "        <div class=\"char-counter\" id=\"charCounter\">Characters left: 5</div>\r\n"
				+ "\r\n"
				+ "        <input type=\"submit\" value=\"Submit\">\r\n"
				+ "    </form>\r\n"
				+ "\r\n"
				+ "<br/>\r\n"
				+ "     <script>\r\n"
				+ "        function limitAndValidateSalary() {\r\n"
				+ "            const salaryInput = document.getElementById(\"salary\");\r\n"
				+ "            const charCounter = document.getElementById(\"charCounter\");\r\n"
				+ "            const maxSalaryLength = 5;\r\n"
				+ "\r\n"
				+ "            // Remove non-numeric characters\r\n"
				+ "            salaryInput.value = salaryInput.value.replace(/\\D/g, \"\");\r\n"
				+ "\r\n"
				+ "            // Limit the maximum length\r\n"
				+ "            if (salaryInput.value.length > maxSalaryLength) {\r\n"
				+ "                salaryInput.value = salaryInput.value.slice(0, maxSalaryLength);\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            // Update character counter\r\n"
				+ "            const remainingChars = maxSalaryLength - salaryInput.value.length;\r\n"
				+ "            charCounter.innerText = `Characters left: ${remainingChars}`;\r\n"
				+ "        }\r\n"
				+ "    </script> \r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "");
		
		
		
		
		
//		out.print("<form action='EditServlet2' method='post'>");
//		out.print("<table>");
//		out.print("<tr><td></td><td><input type='hidden' name='id' value='"+e.getEmployeeId()+"'/></td></tr>");
//		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+e.getName()+"'/></td></tr>");
//		out.print("<tr><td>Birth Date::</td><td><input type='date' name='birthdate' value='"+e.getBirthDate()+"'/></td></tr>");
//		out.print("<tr><td>Address (Max 150 characters):</td><td><textarea name='address' value='"+e.getAddress()+"'rows='4' cols='50' required maxlength='150'></textarea></td></tr>");
//		out.print("<tr><td>Gender:</td><td><input type=\"radio\" id=\"male\" name=\"gender\" value=\"1\" required>\r\n"
//				+ "            <label for=\"male\">Male</label>\r\n"
//				+ "            <input type=\"radio\" id=\"female\" name=\"gender\" value=\"2\" required>\r\n"
//				+ "            <label for=\"female\">Female</label></td></tr>");
//		out.print("<tr><td>Salary (Max 5 digits, Numbers only):</td><td><input type='number' name='salary' value='"+e.getSalary()+"'/></td></tr>");
//		out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
//		out.print("</table>");
//		out.print("</form>");
		
		out.close();
	}
}
