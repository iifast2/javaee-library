package jdbc.servlet;

import jdbc.db.DbUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.Book;



@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String yearString = request.getParameter("year");
        String pagesString = request.getParameter("pages");
        int year = 0;
        int pages = 0;
        if(yearString != null && !"".equals(yearString)) {
            year = Integer.parseInt(yearString);
        }
        if(pagesString != null && !"".equals(pagesString)) {
            pages = Integer.parseInt(pagesString);
        }
        
        String option = request.getParameter("option");
        
        if("show".equals(option)) {
			List<Book> booksList = DbUtil.getBooks();
			request.setAttribute("booksList", booksList);
			request.getRequestDispatcher("booksList.jsp").forward(request, response);
			
		}
        else if("add".equals(option)) {
        	DbUtil.insert(title, author, year, pages);
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else if("update".equals(option)) {
        	DbUtil.update(title,author,year,pages);
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else if("delete".equals(option)) {
        	DbUtil.delete(title);
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
		else response.sendError(403);
        
        
    }




}