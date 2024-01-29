package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.ReportAsSingleViolation;
import java.util.List;

@Transactional
@Repository
public class AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Author create(Author author){
        entityManager.persist(author);
        return author;
    }

    public void edit(Author author){
        entityManager.merge(author);
    }
    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }
    public void delete(Author author){
        entityManager.remove(entityManager.contains(author) ? author: entityManager.merge(author));
    }

    public List<Author> findAll(){
        Query query = entityManager.createQuery("SELECT a FROM Author a");
        return query.getResultList();
    }

}
