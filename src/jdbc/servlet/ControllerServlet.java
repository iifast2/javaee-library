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

        String option = request.getParameter("option");
        
        if("show".equals(option)) {
			List<Book> booksList = DbUtil.getBooks();
			request.setAttribute("booksList", booksList);
			request.getRequestDispatcher("booksList.jsp").forward(request, response);
		}
		else response.sendError(403);
    }




}