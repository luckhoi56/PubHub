package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class RemoveTagServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("publishTag.jsp").forward(request, response);
	}
	// to add a new tag
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");

		TagDAO database = DAOUtilities.getTagDAO();
		Tag tempTag = database.getTag(title);
		if (tempTag != null) {
			// ASSERT: tag with isbn already exists

			req.getSession().setAttribute("message", "Title of " + title + " is already in use");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("publishTag.jsp").forward(req, resp);

		} else {

			Tag tag = new Tag();
			tag.setIsbn13(req.getParameter("isbn13"));
			tag.setTitle(req.getParameter("title"));
			

			// Uploading a file requires the data to be sent in "parts", because
			// one HTTP packet might not be big
			// enough anymore for all of the data. Here we get the part that has
			// the file data
			
			boolean isSuccess = database.addTag(tag);
			
			if(isSuccess){
				req.getSession().setAttribute("message", "Tag successfully published");
				req.getSession().setAttribute("messageClass", "alert-success");

				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				resp.sendRedirect(req.getContextPath() + "/TagPublishing");
			}else {
				req.getSession().setAttribute("message", "There was a problem publishing the tag");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("publishTag.jsp").forward(req, resp);
				
			}
		}
	}

}
