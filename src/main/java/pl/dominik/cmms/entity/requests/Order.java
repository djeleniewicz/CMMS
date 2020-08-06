package pl.dominik.cmms.entity.requests;

import org.hibernate.validator.constraints.NotBlank;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.equipment.Parts;
import pl.dominik.cmms.entity.security.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp created;
    private Timestamp ended;
    private String endNote;

    @NotBlank
    @Size(min = 10)
    private String note;

    @ManyToOne
    private Equipment equipment;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "parts_order", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Parts> parts;
    @ManyToOne
    private ProductionBlocked productionBlocked;
    @ManyToOne
    private Priority priority;

    @ManyToOne
    private User reportedBy;
    // tbd user assigned
    @ManyToOne
    private User assignedTo;

    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Parts> getParts() {
        return parts;
    }

    public void setParts(Set<Parts> parts) {
        this.parts = parts;
    }

    public ProductionBlocked getProductionBlocked() {
        return productionBlocked;
    }

    public void setProductionBlocked(ProductionBlocked productionBlocked) {
        this.productionBlocked = productionBlocked;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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
