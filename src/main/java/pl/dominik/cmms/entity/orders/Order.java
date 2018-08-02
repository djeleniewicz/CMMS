package pl.dominik.cmms.entity.orders;

import org.hibernate.validator.constraints.NotBlank;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.mechanics.Mechanic;
import pl.dominik.cmms.entity.security.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date created;
    private Date ended;
    private String end;



    @NotBlank
    @Size(min = 10)
    private String note;


    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Name name;

    @ManyToOne
    private User user;


    @ManyToOne
    private Mechanic mechanic;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

}
