package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
//import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

/*    @Autowired
    private Sequence sequence;*/

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookEntity> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookEntity> findBookByTitle(String title) {
    	List<BookEntity> found = new ArrayList<BookEntity>();
    	for (BookEntity book : ALL_BOOKS) {
    		if (book.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
    			found.add(book);
    		}
    	}
        return found;
    }

    @Override
    public List<BookEntity> findBooksByAuthor(String author) {
    	List<BookEntity> found = new ArrayList<BookEntity>();
    	for (BookEntity book : ALL_BOOKS) {	
    		if (book.isWrittenByAuthor(author)) {
    			found.add(book);
    		}
    	}
        return found;
    }

    @Override
    @NullableId
    public BookEntity save(BookEntity book) {
        ALL_BOOKS.add(book);
        return book;
    }

 /*   @Autowired
    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }*/

    private void addTestBooks() {
        ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "1#Wiliam#Szekspir"));
        ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "2#Hanna#Ożogowska"));
        ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "3#Jan#Parandowski"));
        ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "4#Edmund#Niziurski"));
        ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "5#Zbigniew#Nienacki"));
        ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "6#Aleksander#Fredro"));
        ALL_BOOKS.add(new BookEntity(7L, "Zemsta Sithów", "7#Aleksander#Dumas"));
        ALL_BOOKS.add(new BookEntity(8L, "Pan Starosta", "7#Fryderyk#Skarbek"));
    }
}