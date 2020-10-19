package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {
	public boolean addTag (Book a); //add tag to a book
	public boolean removeTagByISBN (String isbn);
	public List<Tag> getAllTags (String title);
	public List<Book> getTaggedBook (); //retrive all book that tags, will not return book that has null tag
}