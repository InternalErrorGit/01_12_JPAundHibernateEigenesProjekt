package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.AbstractEntity;
import ch.bbw.pg.entity.Country;
import ch.bbw.pg.entity.Identity;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class IdentityPersistence extends Persistence<Identity> {

    IdentityPersistence(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Identity> list() {
        List<Identity> list = entityManager.createNamedQuery("Identity.findAll").getResultList();
        list.sort(Comparator.comparingLong(AbstractEntity::getId));
        return list;
    }

    @Override
    public Identity find(Long id) {
        return entityManager.find(Identity.class, id);
    }
}
