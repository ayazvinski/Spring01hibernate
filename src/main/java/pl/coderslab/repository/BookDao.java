package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    public void edit(Book book) {
        entityManager.merge(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public List<Book> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating=:rating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }

    public List<Book> findAllWithPublisher() {
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher");
        return query.getResultList();
    }

    public List<Book> findAllByPublisher(Publisher publisher) {
       Query query = entityManager.createQuery("SELECT b FROM Book b where b.publisher = :publisher");
                query.setParameter("publisher", publisher);
                return query.getResultList();
    }

    public List<Book> findAllByAuthor(Author author){
        Query query = entityManager.createQuery("SELECT distinct b from Book b join fetch b.authors where :author member of b.authors");
        query.setParameter("author",author);
        return query.getResultList();
    }
}