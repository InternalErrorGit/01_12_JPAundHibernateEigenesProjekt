package ch.bbw.pg.persistence;

import ch.bbw.pg.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author P. Gatzka
 * @version 02.11.2021
 * Project: 01_12_JPAundHibernateEigenesProjekt
 */
public class PersistenceManager {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("GatzkaPersistenceUnit");
    private static final EntityManager entityManager = factory.createEntityManager();

    private static ch.bbw.pg.persistence.Persistence<Country> countryPersistence;
    private static ch.bbw.pg.persistence.Persistence<City> cityPersistence;
    private static ch.bbw.pg.persistence.Persistence<Person> personPersistence;
    private static ch.bbw.pg.persistence.Persistence<Identity> identityPersistence;
    private static ch.bbw.pg.persistence.Persistence<Address> addressPersistence;

    PersistenceManager() {

    }

    public static void prepare() {
        countryPersistence = new CountryPersistence(entityManager);
        cityPersistence = new CityPersistence(entityManager);
        personPersistence = new PersonPersistence(entityManager);
        identityPersistence = new IdentityPersistence(entityManager);
        addressPersistence = new AddressPersistence(entityManager);
    }


    public static CountryPersistence countryPersistence() {
        return (CountryPersistence) (countryPersistence == null ? countryPersistence = new CountryPersistence(entityManager) : countryPersistence);
    }

    public static CityPersistence cityPersistence() {
        return (CityPersistence) (cityPersistence == null ? cityPersistence = new CityPersistence(entityManager) : cityPersistence);
    }

    public static PersonPersistence personPersistence() {
        return (PersonPersistence) (personPersistence == null ? personPersistence = new PersonPersistence(entityManager) : personPersistence);
    }

    public static IdentityPersistence identityPersistence() {
        return (IdentityPersistence) (identityPersistence == null ? identityPersistence = new IdentityPersistence(entityManager) : identityPersistence);
    }

    public static AddressPersistence addressPersistence() {
        return (AddressPersistence) (addressPersistence == null ? addressPersistence = new AddressPersistence(entityManager) : addressPersistence);
    }


}
