package al.adaction.demo.dto;

public class CityRequest {
    private Long id;

    public CityRequest() {
    }

    public CityRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}