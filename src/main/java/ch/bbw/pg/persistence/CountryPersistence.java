package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.AbstractEntity;
import ch.bbw.pg.entity.City;
import ch.bbw.pg.entity.Country;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class CountryPersistence extends Persistence<Country> {

    CountryPersistence(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Country> list() {
        List<Country> list = entityManager.createNamedQuery("Country.findAll").getResultList();
        list.sort(Comparator.comparingLong(AbstractEntity::getId));
        return list;
    }

    @Override
    public Country find(Long id) {
        return entityManager.find(Country.class, id);
    }
}
