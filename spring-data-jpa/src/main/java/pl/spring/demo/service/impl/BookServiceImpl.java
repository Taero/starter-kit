package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.MapperToEntityTo;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
    private BookDao bookDao;

    @Override
    @Cacheable("booksCache")
    public List<BookTo> findAllBooks() {
        return MapperToEntityTo.mapToList(bookDao.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return MapperToEntityTo.mapToList(bookDao.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return MapperToEntityTo.mapToList(bookDao.findBooksByAuthor(author));
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return MapperToEntityTo.mapTo(bookDao.save(MapperToEntityTo.mapEntity(book)));
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}