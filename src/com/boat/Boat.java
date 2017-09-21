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
 * Servlet implementation class Hello
 */
@WebServlet("/boat")
public class Boat extends HttpServlet {


	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	PrintWriter out = new PrintWriter( new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);

    	// value from web
    	request.setCharacterEncoding("UTF-8");
    	String name = request.getParameter("name");

		// request and response RESTful
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		// request xml
		WebResource path = service.path("rest").path("boat").path("findBoatByName");
		if(name != null) {
			path = path.path(name);
		}else{
			name = "";
		}
		ClientResponse clientXmlResponse = path .accept(MediaType.TEXT_XML).get(ClientResponse.class);
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

//	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList  = doc.getElementsByTagName("boat");

		// out put web
		response.setContentType( "text/html; charset=UTF-8" );
		out.print("<html>");
		out.println("<head><base href=\"http://localhost:8080/marineford-client/\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\r\n");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");
		out.println("</head>");
		out.print("<body>");
//		navbar
		out.print("<nav class=\"navbar navbar-inverse\">");
		out.print("<div class=\"container-fluid\">");
		out.print("<div class=\"navbar-header\">");
		out.print("<a class=\"navbar-brand\" href=\"boat\">");
		out.print("<img alt=\"Marine\" src=\"...\">");
		out.print("</a></div>");
		out.print("<form class=\"navbar-form navbar-left\" role=\"search\">");
		out.print("<div class=\"form-group\">");
		out.print("<input type=\"text\" class=\"form-control\" placeholder=\"Search\" name=\"name\" value=\"\""+name+"\">");
		out.print("</div>");
		out.print("<button type=\"submit\" class=\"btn btn-default\">Submit</button>");
		out.print("</form>");
		out.print("</div></nav>");
//		endnav


		out.println("<center>");


		out.println("<img src=\"https://image.flaticon.com/icons/svg/427/427118.svg\"  width=\"100px\"></img>");
		out.println("<form action=\"boat\">");
//		out.println("<div class=\"col-md-4\">");
//		out.println("<input class=\"form-control input-lg\" type=\"text\" placeholder=\"Find Boat \" name=\"name\" value=\""+name+"\">");
//		out.println("<button type=\"submit\" class=\"btn btn-default\" value=\"Search\">Search</button>");
//		out.println("</div>");
		out.println("</form>");
		out.println("<a href=\"create\" type=\"button\" class=\"btn btn-success btn-lg\">Create</a>");
//		out.println("<a href=\"create\">Create</a>");
		out.println("<br><br><br>");
		out.println("<div class=\"container\">");
		out.println("<table border=\"0\" class=\"table\">");
		out.println("<tr class=\"info\">");
		out.println("<th>Boat_id</th>");
		out.println("<th>Boat name</th>");
		out.println("<th>type</th>");
		out.println("<th>maxseat</th>");
		out.println("<th></th>");
		out.println("<th></th>");
		out.println("<tr>");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				out.println("<tr>");
				out.println("<td>"+eElement.getElementsByTagName("boat_id").item(0).getTextContent()+"</td>");
				out.println("<td>"+eElement.getElementsByTagName("name").item(0).getTextContent()+"</td>");
				out.println("<td>"+eElement.getElementsByTagName("type").item(0).getTextContent()+"</td>");
				out.println("<td>"+eElement.getElementsByTagName("maxseat").item(0).getTextContent()+"</td>");
				String id = eElement.getElementsByTagName("id").item(0).getTextContent();
				out.println("<td><a href=\"update?id="+id+"\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span> Edit</a></td>");
				out.println("<td><a href=\"delete?id="+id+"\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span> Delete</a></td>");
				out.println("</tr>");
			}
		}
		out.println("</table");
		out.println("</div>");
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
