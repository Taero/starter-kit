package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.mapper.MapperToEntityTo;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(9, allBooks.size());
    }

    @Test
//    @Ignore
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookEntity bookToSave = new BookEntity();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(MapperToEntityTo.mapTo(bookToSave));
        // then
        fail("test should throw BookNotNullIdException");
    }

    @Test
    public void testShouldSetIdToBook() {
        // given
        final BookEntity bookToSave = new BookEntity();
        bookToSave.addAuthor("7#Franek#Kimono");
        bookToSave.setTitle("Jestem karate mistrz");
        // when
        bookService.saveBook(MapperToEntityTo.mapTo(bookToSave));
        List<BookTo> booksTo = bookService.findBooksByAuthor("Kimono");
        // then
        assertEquals(1, booksTo.size());
        assertNotNull(booksTo.get(0).getId());
    }
    
    @Test
    public void testShouldFindTwoBooksByTitle() {
    	// given
    	final String title = "zEmStA";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    	assertEquals(2, booksByTitle.size());    	
    }

    @Test
    public void testShouldFindTwoBooksByFirstName() {
    	// given
    	final String author = "aleks";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(2, booksByAuthor.size());    	
    }
    
    @Test
    public void testShouldFindBookByLastName() {
    	// given
    	final String author = "szekspir";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(1, booksByAuthor.size());    	
    }

    @Test
    public void testShouldFindTwoBooksByFirstAndLastName() {
    	// given
    	final String author = "fr";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(2, booksByAuthor.size());    	
    }
    
    @Test
    public void testShouldFindNothingByAuthor() {
    	// given
    	final String author = "Czechowicz";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertTrue(booksByAuthor.isEmpty());
    }
}
