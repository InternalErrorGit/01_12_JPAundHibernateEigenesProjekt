package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.AbstractEntity;
import ch.bbw.pg.entity.Address;
import ch.bbw.pg.entity.City;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class CityPersistence extends Persistence<City> {
    CityPersistence(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<City> list() {
        List<City> list = entityManager.createNamedQuery("City.findAll").getResultList();
        list.sort(Comparator.comparingLong(AbstractEntity::getId));
        return list;
    }

    @Override
    public City find(Long id) {
        return entityManager.find(City.class, id);
    }
}
