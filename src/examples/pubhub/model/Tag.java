package examples.pubhub.model;

public class Tag {
	private String isbn13;
	private String title;
	
	//default constructor
	public Tag() {
		this.isbn13 = null;
		this.title = null;
	}
	public Tag(String isbn, String title) {
		this.isbn13 = isbn;
		this.title = title;
	}
	
	public String getIsbn13() {
		return isbn13;
		
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
