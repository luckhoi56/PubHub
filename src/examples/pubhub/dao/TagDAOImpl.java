package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO{
	Connection connection = null;
	PreparedStatement stmt = null;
	@Override
	public boolean addTag(Book book) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES(?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, book.getTitle());
			
			//if we can add our book to db, we want it to return true
			//this if statement both execute our querys and look at the return
			if(stmt.executeUpdate() != 0)
				return true;
			else
				return false;
;		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeResources();
		}
	}

	@Override
	public boolean removeTagByISBN(String isbn) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE Tags WHERE isbn_13=?";
			stmt.setString(1, isbn);
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeResources();
		}
	}

	@Override
	public List<Tag> getAllTags(Book a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBooks(Tag a) {
		// TODO Auto-generated method stub
		return null;
	}
	private void closeResources() {
		try {
			if(stmt != null)
				stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Could not close statement");
			e.printStackTrace();
		}
		try {
			if(connection != null)
				connection.close();
		}
		catch(SQLException e) {
			System.out.println("Could not close connection0");
			e.printStackTrace();
		}
	}

}
