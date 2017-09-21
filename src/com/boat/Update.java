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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Create
 */
@WebServlet("/update")
public class Update extends HttpServlet {
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
    			
    	// request xml
    	ClientResponse clientXmlResponse = service.path("rest").path("boat").path("getUpdate").path(id) .accept(MediaType.TEXT_XML).get(ClientResponse.class);
    	String outputFromXml =  clientXmlResponse.getEntity(String.class);
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
    	DocumentBuilder builder;  
    	Document doc = null;
    	try  
    	{  
    		builder = factory.newDocumentBuilder();  
    		doc = builder.parse( new InputSource( new StringReader( outputFromXml ) ) );
    		doc.getDocumentElement().normalize();
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
    	
    	NodeList nList  = doc.getElementsByTagName("boat");
		
		// out put web
    	out.print("<html>");
    	out.println("<head><base href=\"http://localhost:8080/marineford-client/\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\r\n");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");
		out.println("</head>");
		out.println("<body>");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				
//				
//				out.println("<div class=\"jumbotron\">");
//				out.println("<div class=\"container\">");
//				out.println("<h1> Update </h1>");
//				out.println("<hr>");
//				out.println("<form class=\"form-horizontal\" action=\"save\">");
//				out.println("<input type=\"hidden\" name=\"id\" value=\""+id+"\"><br>");
//				out.println("<label for=\"inputEmail3\" class=\"col-sm-3 control-label\">Boat ID :</label>");
//				out.println("<div class=\"col-sm-5\">");
//				out.println("<input type=\"text\" name=\"boat_id\" class=\"form-control\"  value=\""+eElement.getElementsByTagName("boat_id").item(0).getTextContent()+"\" >");
//				out.println("</div>");
//				out.println("</form>");
//				
//				
//				out.println("<form class=\"form-horizontal\" action=\"save\">");
//				out.println("<label for=\"inputEmail3\" class=\"col-sm-3 control-label\">Boat ID :</label>");
//				out.println("<div class=\"col-sm-5\">");
//				out.println("<input type=\"text\" name=\"boat_id\" class=\"form-control\"  value=\""+eElement.getElementsByTagName("boat_id").item(0).getTextContent()+"\" >");
//				out.println("</div>");
//				out.println("</form>");
//				
//				out.println("</div>");
//				out.println("</div>");
				
				
				
				
				
				out.println("<br>");
			
				out.println("<div class=\"container\">");
				out.println("<div class=\"jumbotron\">");
				
				out.print("<h1>Update Boat</h1>");
				
				out.println("<form action=\"save\">");
				out.println("<input type=\"hidden\" name=\"id\" value=\""+id+"\"><br>");
				out.println("<be>");
				out.println("boat_id: <input type=\"text\" class=\"form-control\" name=\"boat_id\" value=\""+eElement.getElementsByTagName("boat_id").item(0).getTextContent()+"\"><br>");
				out.println("name : <input type=\"text\"class=\"form-control\" name=\"name\" value=\""+eElement.getElementsByTagName("name").item(0).getTextContent()+"\"><br>");
				out.println("type: <input type=\"text\" class=\"form-control\" name=\"type\" value=\""+eElement.getElementsByTagName("type").item(0).getTextContent()+"\"><br>");
				out.println("maxseat: <input type=\"text\" class=\"form-control\" name=\"maxseat\" value=\""+eElement.getElementsByTagName("maxseat").item(0).getTextContent()+"\"><br>");
				out.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Update\">");
				out.println("</form>");
				
			}
		}
		
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
