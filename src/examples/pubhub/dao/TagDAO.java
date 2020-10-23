package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {
	public boolean addTag (Tag tag); //add tag to a book
	public boolean removeTagByISBN (String isbn);
	public List<Tag> getAllTags (String title); // get all tags for a given book
	public List<Tag> getAllTags() ; // get all tags from book_tags database
	public List<Book> getTaggedBook (String category); //retrive all book that tags, will not return book that has null tag
	public Tag getTag(String title);
}