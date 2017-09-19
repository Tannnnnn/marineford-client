package marineClient;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;

/**
 * Servlet implementation class Create
 */
@WebServlet("/create")

public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String boat_id = request.getParameter("boat_id");
		
		// out put web
		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/RESTfulClient/\"></head>");
		out.print("<body>");
		
		out.println("<form action=\"save\">");
		out.println("boat_id: <input type=\"text\" name=\"boat_id\" value=\"\"><br>");
		out.println("name : <input type=\"text\" name=\"name\" value=\"\"><br>");
		out.println("type: <input type=\"text\" name=\"type\" value=\"\"><br>");
		out.println("maxseat: <input type=\"text\" name=\"maxseat\" value=\"\"><br>");
		out.println("<input type=\"submit\" value=\"Create\">");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private static URI getBaseURI() { 
		return UriBuilder.fromUri( "http://localhost:8080/RESTfulDemo/").build(); 
	}

}
