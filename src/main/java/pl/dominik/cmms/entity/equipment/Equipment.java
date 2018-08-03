package pl.dominik.cmms.entity.equipment;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String name;
    @NotNull
    private Date yearOfProduction;

    @ManyToOne
    private Inspection inspection;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Status status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Date yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "Id=" + id + " | name=" + name + " | location=" + location;
    }
}
