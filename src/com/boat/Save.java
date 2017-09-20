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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class Create
 */
@WebServlet("/save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String id = request.getParameter("id");
    String boat_id = request.getParameter("boat_id");
    	String name = request.getParameter("name");
    	String type = request.getParameter("type");
    	String maxseat = request.getParameter("maxseat");
    	
     	System.out.println(id+" "+boat_id+" "+name+" "+type+" "+maxseat);
    	
        MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
        	queryParams.add("boat_id", boat_id);
        	queryParams.add("name", name);
        	queryParams.add("type", type);
        	queryParams.add("maxseat", maxseat);
		
		
		// request and response RESTful
		ClientConfig config = new DefaultClientConfig(); 
		Client client = Client.create(config); 
		WebResource service = client.resource(getBaseURI()); 
		WebResource path = service.path("rest").path("boat");
		
		if(id == null) {
			path = path.path("create");
		}else {
			queryParams.add("id", id);
			path = path.path("update");
		}
				
		ClientResponse clientResponsePost = path .accept(MediaType.TEXT_PLAIN).post(ClientResponse.class, queryParams);
		
		String result = "";
		if (clientResponsePost.getStatus() != 200) {
			result = "Can't Save";
		}else {
			result = "Save Successful";
		}

//		String outputFromPost = clientResponsePost.getEntity(String.class);
//		out.print(outputFromPost);
		
		// out put web
		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/marineford-client/\"></head>");
		out.print("<body>");
		out.println("<center>");
		out.println("<h2>Result</h2>");
		out.println(result+"<br><br>");
		out.println("<a href=\"boat\">Back to Home</a>");
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
