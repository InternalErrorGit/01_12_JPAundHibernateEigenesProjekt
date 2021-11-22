package ch.bbw.pg.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "country")
@Entity
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "country_cities",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "cities_id"))
    private Set<City> cities;

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}