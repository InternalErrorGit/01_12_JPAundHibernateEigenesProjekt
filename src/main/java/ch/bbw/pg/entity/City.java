package ch.bbw.pg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "city")
@Entity
@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
public class City extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}