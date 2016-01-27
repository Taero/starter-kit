package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.List;

public class BookEntity implements IdAware {
	private final static String AUTHOR_SEPARATOR_FIELD = "#";
	private final static String AUTHOR_SEPARATOR_ROW = ";";

	private Long id;
	private String title;
	private List<AuthorEntity> authors;    

	public BookEntity() {	
	}

	public BookEntity(Long id, String title, String authors) {
		this.authors = new ArrayList<AuthorEntity>();
		this.id = id;
		this.title = title;
		setAuthors(authors);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		String result = "";

		if (authors == null) {
			return result;
		}
		for (AuthorEntity author : this.authors)
		{ 
			if (!result.equals(""))
				result += AUTHOR_SEPARATOR_ROW;
			result+= author.getId() + AUTHOR_SEPARATOR_FIELD + author.getFirstName() + AUTHOR_SEPARATOR_FIELD + author.getLastName();
		}
		return result;
	}

	public void setAuthors(String authors) {
		String[] authorsList = authors.split(BookEntity.AUTHOR_SEPARATOR_ROW);
		for(String author : authorsList)
			this.addAuthor(author);
	}

	private void addAuthor(AuthorEntity author) {
		if (!authors.contains(author))
			authors.add(author);
	}

	public void addAuthor(String author) {
		String[] singleAuthor;

		if (author != null) {
			singleAuthor = author.split(BookEntity.AUTHOR_SEPARATOR_FIELD);
			if (singleAuthor.length == 3)
				this.addAuthor(new AuthorEntity(singleAuthor[0], singleAuthor[1], singleAuthor[2]));	
		}
	}
	
	public boolean isWrittenByAuthor(String authorPattern) {
		for (AuthorEntity author : authors) {
			if (author.getFirstName().toLowerCase().startsWith(authorPattern.toLowerCase()) || author.getLastName().toLowerCase().startsWith(authorPattern.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}