package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    public void edit(Book book){
         entityManager.merge(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book){
        entityManager.remove(entityManager.contains(book) ? book: entityManager.merge(book));
    }
}
