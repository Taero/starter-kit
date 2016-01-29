package pl.spring.demo.entity;

import java.util.ArrayList;
import java.util.List;

import pl.spring.demo.to.IdAware;

public class BookEntity implements IdAware {
	private final static String AUTHOR_SEPARATOR_FIELD = "#";
	private final static String AUTHOR_SEPARATOR_ROW = ";";

	private Long id;
	private String title;
	private List<AuthorEntity> authors;    

	public BookEntity() {	
		this.authors = new ArrayList<AuthorEntity>();
		title = "";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!getAuthors().equals(other.getAuthors()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}