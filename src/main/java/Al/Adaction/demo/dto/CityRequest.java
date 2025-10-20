package al.adaction.demo.dto;

public class CityRequest {
    private Long id;
    private String name;

    public CityRequest() {
    }

    public CityRequest(Long id) {
        this.id = id;
    }

    public CityRequest(Long id, String name) {
        this.id = id;
        this.name = name;
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
}