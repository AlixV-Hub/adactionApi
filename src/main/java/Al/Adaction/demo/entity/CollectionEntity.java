package al.adaction.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "waste_collection")
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "collection_date", nullable = false)
    private LocalDate collectionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relations
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WasteCollectionItemEntity> wasteCollectionItems = new ArrayList<>();

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WasteVolunteerCollectionEntity> volunteerCollections = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = "PLANNED";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public CollectionEntity() {}

    public CollectionEntity(LocalDate collectionDate, CityEntity city) {
        this.collectionDate = collectionDate;
        this.city = city;
    }

    public void addWasteCollectionItem(WasteCollectionItemEntity item) {
        wasteCollectionItems.add(item);
        item.setCollection(this);
    }

    public void removeWasteCollectionItem(WasteCollectionItemEntity item) {
        wasteCollectionItems.remove(item);
        item.setCollection(null);
    }

    public boolean isPlanned() {
        return "PLANNED".equals(status);
    }

    public boolean isInProgress() {
        return "IN_PROGRESS".equals(status);
    }

    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }

    public boolean isCancelled() {
        return "CANCELLED".equals(status);
    }

    public void start() {
        if (!isPlanned()) {
            throw new IllegalStateException("Seule une collecte planifiée peut être démarrée");
        }
        this.status = "IN_PROGRESS";
    }

    public void complete() {
        if (!isInProgress()) {
            throw new IllegalStateException("Seule une collecte en cours peut être terminée");
        }
        this.status = "COMPLETED";
    }

    public void cancel() {
        if (isCompleted()) {
            throw new IllegalStateException("Impossible d'annuler une collecte terminée");
        }
        this.status = "CANCELLED";
    }

    public Long getCityId() {
        return city != null ? city.getId() : null;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<WasteCollectionItemEntity> getWasteCollectionItems() {
        return wasteCollectionItems;
    }

    public void setWasteCollectionItems(List<WasteCollectionItemEntity> wasteCollectionItems) {
        this.wasteCollectionItems = wasteCollectionItems;
    }

    public List<WasteVolunteerCollectionEntity> getVolunteerCollections() {
        return volunteerCollections;
    }

    public void setVolunteerCollections(List<WasteVolunteerCollectionEntity> volunteerCollections) {
        this.volunteerCollections = volunteerCollections;
    }
}