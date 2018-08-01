package pl.dominik.cmms.entity.orders;

import pl.dominik.cmms.entity.security.User;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Time created;
    private Time ended;

    @ManyToOne
    private Name name;

    @ManyToOne
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getCreated() {
        return created;
    }

    public void setCreated(Time created) {
        this.created = created;
    }

    public Time getEnded() {
        return ended;
    }

    public void setEnded(Time ended) {
        this.ended = ended;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
