package com.boat;

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
    		String id = request.getParameter("id");
//    		String boat_id = request.getParameter("boat_id");

		// out put web
    		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/marineford-client/\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\r\n");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");
		out.println("</head>");
		out.print("<body>");

		out.print("<div class=\"container\">");
		out.print("<br><br><br>");
		out.print("<div class=\"jumbotron\">");
		out.print("<h1>Create Boat</h1>");
		out.print("<p>Let's add more boat in our company</p>");
		out.println("<form action=\"save\">");
		out.println("boat_id: <input type=\"text\" class=\"form-control\" name=\"boat_id\" value=\"\"><br>");
		out.println("name : <input type=\"text\"class=\"form-control\" name=\"name\" value=\"\"><br>");
		out.println("type: <input type=\"text\" class=\"form-control\" name=\"type\" value=\"\"><br>");
		out.println("maxseat: <input type=\"text\" class=\"form-control\" name=\"maxseat\" value=\"\"><br>");
		out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Create\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");

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
		return UriBuilder.fromUri( "http://localhost:8080/marineford-service/").build();
	}
}
