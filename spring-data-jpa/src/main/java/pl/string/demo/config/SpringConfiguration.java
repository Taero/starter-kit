package pl.string.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.impl.BookServiceImpl;

@Configuration
@ComponentScan("pl.spring.demo.*")
//@EnableAspectJAutoProxy
@PropertySource("classpath:config/application.properties")

public class SpringConfiguration {

	@Bean
	public BookService bookService() {
		return new BookServiceImpl();
	}

	@Bean
	public BookDao bookDao() {
		return new BookDaoImpl();
	}

	@Bean
	public Sequence sequence() {
		return new Sequence();
	}

/*	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
}	
