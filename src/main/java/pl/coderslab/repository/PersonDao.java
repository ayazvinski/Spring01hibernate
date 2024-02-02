package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }

    public void edit(Person person) {
        entityManager.merge(person);
    }

    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }

    public void delete(Person person) {
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
    }

}
