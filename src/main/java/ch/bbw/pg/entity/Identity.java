package ch.bbw.pg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Date;
import java.util.UUID;

@Table(name = "identity")
@Entity
@NamedQuery(name = "Identity.findAll", query = "SELECT i FROM Identity i")
public class Identity extends AbstractEntity {
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public static Identity createIdentity() {
        Identity identity = new Identity();
        identity.setUuid(UUID.randomUUID().toString());
        return identity;
    }

}