package al.adaction.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;     // Pour WasteCollectionItemsEntity
import jakarta.persistence.*;

@Entity
@Table(name = "waste_collection_items")
public class WasteCollectionItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonBackReference
    private CollectsEntity collection;

    @ManyToOne
    @JoinColumn(name = "waste_type_id", nullable = false)
    private WasteTypesEntity wasteType;

    @Column(nullable = false)
    private Double quantity = 0.0; // valeur par défaut 0

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CollectsEntity getCollection() { return collection; }
    public void setCollection(CollectsEntity collection) { this.collection = collection; }

    public WasteTypesEntity getWasteType() { return wasteType; }
    public void setWasteType(WasteTypesEntity wasteType) { this.wasteType = wasteType; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
}
