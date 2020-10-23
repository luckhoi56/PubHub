package examples.pubhub.test;

import java.util.List;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;
import examples.pubhub.model.*;
/*
 * 
 * For methods to test
 * public boolean addTag (Book a); //add tag to a book
	public boolean removeTagByISBN (String isbn);
	public List<Tag> getAllTags (String title);
	public List<Book> getTaggedBook ();
 */
public class TestTagDao {
	public static void main(String[] args) {
		TagDAO dao = new TagDAOImpl();
		//Book book = new Book("1111111111112","Anh Hung Viet Nam","Trinh Cong Son",null); //will be added
		//Book book_2 = new Book("1111111111113","Wall Street Bets","Khoi Luc",null);
		//dao.addTag(book_2);
		//System.out.println("Tag is: " + dao.addTag(book));
		List<Book> list = dao.getTaggedBook("investing");
		for (int i = 0; i < list.size(); i++) {
			Book b = list.get(i);
			System.out.println(b.getIsbn13());
			System.out.println(b.getAuthor());
		}
	}
}
