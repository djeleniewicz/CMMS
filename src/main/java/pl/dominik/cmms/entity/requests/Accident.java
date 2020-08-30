package pl.dominik.cmms.entity.requests;

import org.hibernate.validator.constraints.NotBlank;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.equipment.Status;
import pl.dominik.cmms.entity.security.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "accidents")
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDateTime created;
    private LocalDateTime ended;
    private String endNote;
    private boolean dangerous;
//    private double durationOffline;

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

    @ManyToOne
    private User assignedTo;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Priority priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    public void setEnded(LocalDateTime ended) {
        this.ended = ended;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
}
