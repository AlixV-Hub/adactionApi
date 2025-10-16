package al.adaction.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waste_types")
public class WasteTypesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "label", nullable = false)
    private String name;

    @Column(name = "classname")
    private String classname;


    public WasteTypesEntity() {
    }

    public WasteTypesEntity(String value, String name, String classname) {
        this.value = value;
        this.name = name;
        this.classname = classname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }


    public String getDescription() {
        return this.name;
    }

    public void setDescription(String description) {
        this.name = description;
    }

    //  toString pour la lisibilité des objets
    @Override
    public String toString() {
        return "WasteTypesEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
