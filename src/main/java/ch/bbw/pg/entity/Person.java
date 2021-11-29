package ch.bbw.pg.entity;

import javax.persistence.*;

@Table(name = "person")
@Entity
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person extends AbstractEntity {
    @OneToOne(cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "identity_id", nullable = false)
    private Identity identity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public static String[] getTableHeader() {
        return new String[]{"ID", "Firstname", "Lastname", "Identity_ID", "Address_ID"};
    }

    @Override
    public Object[] toTableData() {
        return new Object[]{getId(), getFirstname(), getLastname(), getIdentity().getId(), getAddress().getId()};
    }
}