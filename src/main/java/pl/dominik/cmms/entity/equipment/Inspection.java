package pl.dominik.cmms.entity.equipment;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "inspection")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date dateInspection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateInspection() {
        return dateInspection;
    }

    public void setDateInspection(Date dateInspection) {
        this.dateInspection = dateInspection;
    }
}
