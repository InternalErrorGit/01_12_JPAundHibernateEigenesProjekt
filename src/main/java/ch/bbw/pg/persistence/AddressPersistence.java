package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.AbstractEntity;
import ch.bbw.pg.entity.Address;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class AddressPersistence extends Persistence<Address> {

    AddressPersistence(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Address> list() {
        List<Address> list = entityManager.createNamedQuery("Address.findAll").getResultList();
        list.sort(Comparator.comparingLong(AbstractEntity::getId));
        return list;
    }

    @Override
    public Address find(Long id) {
        return entityManager.find(Address.class, id);
    }
}
