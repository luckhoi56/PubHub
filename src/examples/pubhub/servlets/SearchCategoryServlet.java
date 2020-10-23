package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/SearchCategory")
public class SearchCategoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("searchCategory.jsp").forward(request, response);
	}
	// to add a new tag
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String category = request.getParameter("category");
		System.out.println(category);
		TagDAO database = DAOUtilities.getTagDAO();
		//List<Book> bookList = database.getTaggedBook(category);
		List<Book> bookList = database.getTaggedBook("investing");
		System.out.println(bookList);
		request.getSession().setAttribute("books", bookList);
		
		request.getRequestDispatcher("searchedCategory.jsp").forward(request, response);
		}
	}


