package ch.bbw.pg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "country")
@Entity
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String[] getTableHeader() {
        return new String[]{"ID", "Name"};
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object[] toTableData() {
        return new Object[]{getId(), getName()};
    }
}