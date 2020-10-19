package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {
	public boolean addTag (Book a); //add tag to a book
	public boolean removeTag (Book a);
	public List<Tag> getAllTags (Book a);
	
}