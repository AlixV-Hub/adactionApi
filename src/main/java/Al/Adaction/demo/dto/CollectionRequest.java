package al.adaction.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class CollectionRequest {

    private LocalDate collectionDate;
    private CityRequest city;
    private List<Long> volunteerIds;
    private List<WasteCollectionItem> wasteCollectionItems;


    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public CityRequest getCity() {
        return city;
    }

    public void setCity(CityRequest city) {
        this.city = city;
    }

    public List<Long> getVolunteerIds() {
        return volunteerIds;
    }

    public void setVolunteerIds(List<Long> volunteerIds) {
        this.volunteerIds = volunteerIds;
    }

    public List<WasteCollectionItem> getWasteCollectionItems() {
        return wasteCollectionItems;
    }

    public void setWasteCollectionItems(List<WasteCollectionItem> wasteCollectionItems) {
        this.wasteCollectionItems = wasteCollectionItems;
    }

    // Classe interne pour les items de déchets
    public static class WasteCollectionItem {
        private WasteType wasteType;
        private double quantity;

        public WasteType getWasteType() {
            return wasteType;
        }

        public void setWasteType(WasteType wasteType) {
            this.wasteType = wasteType;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }
    }

    public static class WasteType {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}