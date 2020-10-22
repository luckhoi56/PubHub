package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.*;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO{
	Connection connection = null;
	PreparedStatement stmt = null;
	@Override
	public boolean addTag(Tag tag) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES(?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getTitle());
			
			//if we can add our book to db, we want it to return true
			//this if statement both execute our querys and look at the return
			if(stmt.executeUpdate() != 0)
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
	public boolean removeTagByISBN(String isbn) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
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
	//get all tags for a given book
	public List<Tag> getAllTags(String title) {
		
		// TODO Auto-generated method stub
		List<Tag> tags = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE title LIKE ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + title + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Tag tag = new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTitle(rs.getString("tag_name"));
				tags.add(tag);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		return tags;
	}
	
	@Override
	//get all tags for a given book
	public List<Tag> getAllTags() {
		List<Tag> tags = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Tag tag = new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTitle(rs.getString("tag_name"));
				tags.add(tag);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		return tags;
	}
	@Override
public Tag getTag(String isbn) {
		
		Tag tag = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				tag = new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				
				tag.setTitle(rs.getString("tag_name"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tag;
	}
	
	@Override
	//given a tag isbn, retrieve books
	//assume a book can have more than one tag string, or tag isbn
	//select * from book_tags inner join Books on book_tags.isbn_13 = Books.isbn_13 
	public List<Book> getTaggedBook() {
		// TODO Auto-generated method stub
		
		List<Book> books = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "select * from Books inner join book_tags on book_tags.isbn_13 = Books.isbn_13 ";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		return books;
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
