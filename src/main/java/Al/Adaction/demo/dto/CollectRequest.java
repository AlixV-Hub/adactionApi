package al.adaction.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class CollectRequest {

    private LocalDate collectionDate;
    private City city;
    private List<Long> volonteerIds; // ✅ Ajouté
    private List<WasteCollectionItem> wasteCollectionItems;

    // --- Getters & Setters ---
    public LocalDate getCollectionDate() { return collectionDate; }
    public void setCollectionDate(LocalDate collectionDate) { this.collectionDate = collectionDate; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public List<Long> getVolonteerIds() { return volonteerIds; }
    public void setVolonteerIds(List<Long> volonteerIds) { this.volonteerIds = volonteerIds; }

    public List<WasteCollectionItem> getWasteCollectionItems() { return wasteCollectionItems; }
    public void setWasteCollectionItems(List<WasteCollectionItem> wasteCollectionItems) { this.wasteCollectionItems = wasteCollectionItems; }

    public LocalDate getDate() {
        return null;
    }

    public Long getCityId() {
        return 0L;
    }

    // Classes internes pour la structure imbriquée
    public static class WasteCollectionItem {
        private WasteType wasteType;
        private double quantity;

        public WasteType getWasteType() { return wasteType; }
        public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }

        public double getQuantity() { return quantity; }
        public void setQuantity(double quantity) { this.quantity = quantity; }
    }

    public static class WasteType {
        private Long id;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }

    public static class City {
        private Long id;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }
}
