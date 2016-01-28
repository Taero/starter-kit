package pl.spring.demo.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.MapperToEntityTo;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
    	BookEntity bookEntity = new BookEntity(null, "title", "90#herr#author");
//      Mockito.when(bookDao.save(book)).thenReturn(new BookTo(1L, "title", "author"));
    	BookEntity mockedBookEntity = new BookEntity(1L, "title", "90#herr#author");
        Mockito.when(bookDao.save(bookEntity)).thenReturn(mockedBookEntity);
        // when
        BookTo bookTo = MapperToEntityTo.mapTo(bookEntity);
        BookTo result = bookService.saveBook(bookTo);
        // then
        Mockito.verify(bookDao).save(bookEntity);
        assertEquals(1L, result.getId().longValue());
    }
}
