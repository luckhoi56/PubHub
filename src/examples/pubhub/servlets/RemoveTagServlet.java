package examples.pubhub.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import examples.pubhub.dao.TagDAO;

import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig // This annotation tells the server that this servlet has
					// complex data other than forms
// Notice the lack of the @WebServlet annotation? This servlet is mapped the old
// fashioned way - Check the web.xml!
@WebServlet("/RemoveTag")
public class RemoveTagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("removeTag.jsp").forward(request, response);
	}
	// to add a new tag
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn13");

		TagDAO database = DAOUtilities.getTagDAO();
		Tag tempTag = database.getTag(isbn13);
		if (tempTag == null) {
			// ASSERT: tag with isbn already exists

			req.getSession().setAttribute("message", "isbn13 " + isbn13 + " does not exist");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("removeTag.jsp").forward(req, resp);

		} else {

			
			boolean isSuccess = database.removeTagByISBN(isbn13);
			
			if(isSuccess){
				req.getSession().setAttribute("message", "Tag successfully removed");
				req.getSession().setAttribute("messageClass", "alert-success");

				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				resp.sendRedirect(req.getContextPath() + "/TagPublishing");
			}else {
				req.getSession().setAttribute("message", "There was a problem removing the tag");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("tagPublishingHome.jsp").forward(req, resp);
				
			}
		}
	}

}
