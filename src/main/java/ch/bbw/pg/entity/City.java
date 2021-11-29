package ch.bbw.pg.entity;

import javax.persistence.*;

@Table(name = "city")
@Entity
@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
public class City extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String[] getTableHeader() {
        return new String[]{"ID", "Name", "Country_ID"};
    }

    @Override
    public Object[] toTableData() {
        return new Object[]{getId(), getName(), getCountry().getId()};
    }
}