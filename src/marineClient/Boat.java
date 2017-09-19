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
 * Servlet implementation class Hello
 */
@WebServlet("/Boat")
public class Boat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset-UTF-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
    	
    	// value from web
    	String name = request.getParameter("name");
    	
		// request and response RESTful
		ClientConfig config = new DefaultClientConfig(); 
		Client client = Client.create(config); 
		WebResource service = client.resource(getBaseURI()); 
		WebResource path = service.path("Boat").path("findBoat");
		
		if(name != null) {
			path = path.path(name);
		}else{
			name = "";
		}
		
		// request get
		ClientResponse clientResponse = path .accept(MediaType.TEXT_XML).get(ClientResponse.class);
		
		if (clientResponse.getStatus() != 200) {
			out.print("<html>");
			out.print("<body>");
			out.print("<div>");
			out.print("<h1>");
			out.print("Search not fornd!!!");
			out.print("/h1");
			out.print("</body>");
			out.print("</html>");
		}

		String outputFromXml =  clientResponse.getEntity(String.class);
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
	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
		NodeList nList  = doc.getElementsByTagName("return");
		response.setContentType("text/html;charset-UTF-8");
		out.print("<html>");
		out.print("<head>");
		out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
		out.print("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
		out.print("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		out.print("</head>");
		out.print("<body>");
		out.print("<center>");out.print("<h2>");out.print("Search Results");out.print("</h2>");out.print("</center>");
		out.print("<center>");
		out.println("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<tr>");
		out.print("<th>");out.print("boat_id");out.print("</th>");
		out.print("<th>");out.print("name");out.print("</th>");
		out.print("<th>");out.print("type");out.print("</th>");
		out.print("<th>");out.print("maxseat");out.print("</th>");
		out.print("</tr>");
		
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				out.print("<h2>");
				out.print("<td>");out.println(eElement.getElementsByTagName("boat_id").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("name").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("type").item(0).getTextContent());out.print("</td>");
				out.print("<td>");out.println(eElement.getElementsByTagName("maxseat").item(0).getTextContent());out.print("</td>");
				out.print("</h2>");
			}
		}
		out.print("</table>");
		out.println("</div>");
		out.print("</center>");
		out.print("</body>");
		out.print("</html>");
		
	}
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private static URI getBaseURI() { 
		return UriBuilder.fromUri( "http://localhost:8080/marineford-service/find/").build(); 
	}


}