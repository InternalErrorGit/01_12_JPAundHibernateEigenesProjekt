package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.AbstractEntity;
import ch.bbw.pg.entity.Person;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class PersonPersistence extends Persistence<Person> {

    PersonPersistence(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> list() {
        List<Person> list = entityManager.createNamedQuery("Person.findAll").getResultList();
        list.sort(Comparator.comparingLong(AbstractEntity::getId));
        return list;
    }

    @Override
    public Person find(Long id) {
        return entityManager.find(Person.class, id);
    }
}
