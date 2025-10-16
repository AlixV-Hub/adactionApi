package al.adaction.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waste_collection_volonteers")
public class WasteVolunteerCollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volonteer_id", nullable = false)
    private VolunteerEntity volunteer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private CollectionEntity collection;

    // Constructeurs
    public WasteVolunteerCollectionEntity() {}

    public WasteVolunteerCollectionEntity(VolunteerEntity volunteer, CollectionEntity collection) {
        this.volunteer = volunteer;
        this.collection = collection;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VolunteerEntity getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerEntity volunteer) {
        this.volunteer = volunteer;
    }

    public CollectionEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionEntity collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "WasteVolunteerCollectionEntity{" +
                "id=" + id +
                ", volunteerId=" + (volunteer != null ? volunteer.getId() : null) +
                ", collectionId=" + (collection != null ? collection.getId() : null) +
                '}';
    }
}