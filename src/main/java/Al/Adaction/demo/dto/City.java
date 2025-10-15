// src/main/java/al/adaction/demo/dto/CityDTO.java
package al.adaction.demo.dto;

public class City {
    private Long id;
    private String name;

    // Constructeur par défaut (nécessaire pour la sérialisation JSON)
    public City() {
    }

    // Constructeur avec paramètres
    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
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
}
