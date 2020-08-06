package pl.dominik.cmms.entity.requests;

import org.hibernate.validator.constraints.NotBlank;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.security.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "inspections")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Timestamp created;
    private Timestamp ended;
    private String endNote;
    private boolean dangerous;

    @NotBlank
    @Size(min = 10)
    private String note;
    @ManyToOne
    private Equipment equipment;
    @ManyToOne
    private ProductionBlocked productionBlocked;
    // user detected
    @ManyToOne
    private User reportedBy;
    // tbd user assigned
    @ManyToOne
    private User assignedTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEnded() {
        return ended;
    }

    public void setEnded(Timestamp ended) {
        this.ended = ended;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public ProductionBlocked getProductionBlocked() {
        return productionBlocked;
    }

    public void setProductionBlocked(ProductionBlocked productionBlocked) {
        this.productionBlocked = productionBlocked;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
}
