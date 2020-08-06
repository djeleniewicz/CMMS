package pl.dominik.cmms.entity.equipment;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String codeInternal;
    private String codeExternal;
    private int quantity;
    @ManyToOne
    private Location location;

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

    public String getCodeInternal() {
        return codeInternal;
    }

    public void setCodeInternal(String codeInternal) {
        this.codeInternal = codeInternal;
    }

    public String getCodeExternal() {
        return codeExternal;
    }

    public void setCodeExternal(String codeExternal) {
        this.codeExternal = codeExternal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return name;
    }
}
