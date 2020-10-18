package examples.pubhub.test;

import java.util.List;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookDAOImpl;
import examples.pubhub.model.Book;

public class TestBookDAO {
	public static void main(String[] args) {
		System.out.println("Hello World");
		BookDAO dao = new BookDAOImpl();
		List<Book> list = dao.getBooksByAuthor("Russell Barron");
		for (int i = 0; i < list.size(); i++) {
			Book b = list.get(i);
			System.out.println(b.getTitle());
		}
	}
}
