package al.adaction.demo.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "waste_types")

public class WasteTypesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private String label;
    private String classname;


    public WasteTypesEntity() {
    }
    public WasteTypesEntity(Long id, String classname, String label, String value) {
        this.id = id;
        this.classname = classname;
        this.label = label;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}