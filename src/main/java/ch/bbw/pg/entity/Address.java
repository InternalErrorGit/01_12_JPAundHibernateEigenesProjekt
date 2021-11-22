package ch.bbw.pg.entity;

import javax.persistence.*;

@Table(name = "address")
@Entity
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address extends AbstractEntity {
    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "housenumber", nullable = false)
    private String housenumber;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}