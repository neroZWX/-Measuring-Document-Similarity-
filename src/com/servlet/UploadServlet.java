package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.service.FileService;

public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	FileService fileService = new FileService();
	
    /**
     * upload file and save 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		ServletFileUpload upload = new ServletFileUpload();
		// language..
		upload.setHeaderEncoding("UTF-8");

		FileItemIterator fii;
		try {
			fii = upload.getItemIterator(request);
			while (fii.hasNext()) {
				FileItemStream fis = fii.next();
				List<Map> resultList = fileService.excute(fis);
				outPutResult(request, response, resultList);
			}
		} catch (Exception e) {

		}
			
		
    }
    
	private void outPutResult(HttpServletRequest req, HttpServletResponse res, List<Map> resultList) throws IOException {
		
		// Output response information
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<br/>");
		out.println("document compare success!<a href='index.jsp'>return</a>");
		out.println("<p></p>");
		out.println("<table width='100%' border='1' cellspacing='0'>");
		out.println("	<tr>");
		out.println("		<th>document name</th>");
		out.println("		<th>content</th>");
		out.println("		<th>jaccard index</th>");
		out.println("		<th>minHash</th>");
		out.println("	</tr>");

		if (resultList != null && resultList.size() > 0) {

			for (Map map : resultList) {
				out.println("<tr>");
				out.println("	<td align='center'>" + map.get("name") + "</td>");
				out.println("	<td align='center'>" + map.get("content") + "</td>");
				out.println("	<td align='center'>" + map.get("jaccard") + "</td>");
				out.println("	<td align='center'>" + map.get("minHash") + "</td>");
				out.println("</tr>");
			}
		}
		out.println("</table>");
	}

}
