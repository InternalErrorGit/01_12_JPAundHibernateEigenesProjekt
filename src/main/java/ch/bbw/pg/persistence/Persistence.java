package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public abstract class Persistence<T extends AbstractEntity> {

    protected EntityManager entityManager;

    public Persistence(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public abstract List<T> list();

    public void remove(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(find(id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public abstract T find(Long id);

}
