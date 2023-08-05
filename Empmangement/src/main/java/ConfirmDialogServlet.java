


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ConfirmDialogServlet")
public class ConfirmDialogServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sid=request.getParameter("id");
		String gotoDeleteServlet= "/Empmangement/DeleteServlet?id="+sid;
		String gotoViewList= "/Empmangement/ViewServlet";
		// Prepare the JavaScript code for the alert message
        String confirmMessage = "Are you sure want to delete employee?";
        //String jsCode = "<script type=\"text/javascript\">confirm('" + alertMessage +"');</script>";
        String jsCode = "<script type=\"text/javascript\">var result = confirm('" + confirmMessage + "');"
                + "if (result) {"
                + "    window.location.href = '"+gotoDeleteServlet+"'; "
                + "}"
                + "else {"
                + "    window.location.href = '"+gotoViewList+"'; "
                + "}"
                + "</script>";
        PrintWriter out = response.getWriter();
        out.println(jsCode);

	}
	
	
}
