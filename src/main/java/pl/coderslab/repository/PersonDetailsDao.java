package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDetails create(PersonDetails personDetails) {
        entityManager.persist(personDetails);
        return personDetails;
    }

    public void edit(PersonDetails personDetails) {
        entityManager.merge(personDetails);
    }

    public PersonDetails findById(long id) {
        return entityManager.find(PersonDetails.class, id);
    }

    public void delete(PersonDetails personDetails) {
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
    }
}
