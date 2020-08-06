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
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Date dateOfProduction;
    @ManyToOne
    private Location location;

    private boolean status;
    private String codeInternal;
    private String severity;
    private String model;
    private String producer;
    private int intervalInspectionDays;

    public int getIntervalInspectionDays() {
        return intervalInspectionDays;
    }

    public void setIntervalInspectionDays(int intervalInspectionDays) {
        this.intervalInspectionDays = intervalInspectionDays;
    }

    /*
    tbd
    id instrukcji obsługi
    głowny przegląd, dodatkowy
     */

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public String getCodeInternal() {
        return codeInternal;
    }

    public void setCodeInternal(String codeInternal) {
        this.codeInternal = codeInternal;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Id=" + id + " | name=" + name + " | location=" + location + " | status=" + (this.isStatus() ? "Active" : "Not active");
    }
}
