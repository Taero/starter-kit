package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public class MapperToEntityTo {

	public static BookEntity mapEntity(BookTo bookTo) {
		if (bookTo != null) 
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), bookTo.getAuthors());

		return null;
	}
	
	public static BookTo mapTo(BookEntity bookEntity) {
		if (bookEntity != null) 
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthors());

		return null;
	}
    public static List<BookTo> mapToList(List<BookEntity> bookEntities) {
    	List<BookTo> bookTolist = new ArrayList<BookTo>();
    	for (BookEntity bookEnt : bookEntities){
    		bookTolist.add(MapperToEntityTo.mapTo(bookEnt));
    	}
        return bookTolist;
    }

    public static List<BookEntity> mapEntityList(List<BookTo> bookTos) {
    	List<BookEntity> bookEntitylist = new ArrayList<BookEntity>();
    	for (BookTo bookTo : bookTos){
    		bookEntitylist.add(MapperToEntityTo.mapEntity(bookTo));
    	}
        return bookEntitylist;
    }
}
