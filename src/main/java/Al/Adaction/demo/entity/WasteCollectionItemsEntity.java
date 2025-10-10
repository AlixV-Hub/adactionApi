package al.adaction.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waste_collection_items")
public class WasteCollectionItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation vers CollectsEntity
    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private CollectsEntity collection;

    // Relation vers WasteTypesEntity
    @ManyToOne
    @JoinColumn(name = "waste_type_id", nullable = false)
    private WasteTypesEntity wasteType;

    @Column(nullable = false)
    private Double quantity;

    // ----- Getters et Setters -----
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CollectsEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectsEntity collection) {
        this.collection = collection;
    }

    public WasteTypesEntity getWasteType() {
        return wasteType;
    }

    public void setWasteType(WasteTypesEntity wasteType) {
        this.wasteType = wasteType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
