package pl.spring.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;

import java.lang.reflect.Method;

//@Aspect
//@Component
public class BookDaoAdvisor implements MethodBeforeAdvice {

	@Autowired
    private Sequence sequence;

    @Override
//    @Before("pl.spring.demo.dao.Impl.BookDaoImpl.*")
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0]);
            
            if (objects[0] instanceof IdAware && ((IdAware) objects[0]).getId() == null) {
            	IdAware book = (IdAware) objects[0];
            	book.setId(sequence.nextValue( ((BookDao)o).findAll() ));
            }
        }    
    }
        
    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }

    private boolean hasAnnotation (Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;
        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
}