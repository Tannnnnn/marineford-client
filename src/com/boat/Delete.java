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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Create
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String id = request.getParameter("id");
    	
    	// request and response RESTful
    	ClientConfig config = new DefaultClientConfig(); 
		Client client = Client.create(config); 
		WebResource service = client.resource(getBaseURI()); 
				
		ClientResponse clientResponse = service.path("rest").path("boat").path("delete").path(id) .accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		
		String result = "";
		if (clientResponse.getStatus() != 200) {
			result = "Can't Save";
		}else {
			result = "Save Successful";
		}
		
		// out put web
			
		
		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/marineford-client/\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\r\n");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");


		out.println("</head>");
		out.print("<body>");
		out.println("<center>");
		
		out.println("<h2><div class=\"jumbotron\"><h1>Result</h1>\n" +
				"<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>"+
				"Delete Successful<br><br>\n" + 
				
				"<a href=\"boat\"><button type=\"button\" class=\"btn btn-warning\">Back to Home</button></a>\n" + 
				"</div></h2>");

		out.println("</center>");
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
