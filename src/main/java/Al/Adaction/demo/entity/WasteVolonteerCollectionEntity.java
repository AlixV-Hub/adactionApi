package al.adaction.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waste_collection_volonteers")
class WasteCollectionVolunteerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation avec CollectsEntity
    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private CollectsEntity collection;

    // Relation avec VolonteerEntity
    @ManyToOne
    @JoinColumn(name = "volonteer_id", nullable = false)
    private VolonteerEntity volunteer;

    // Constructeurs
    public WasteCollectionVolunteerEntity() {}

    // Getters et setters
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

    public VolonteerEntity getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolonteerEntity volunteer) {
        this.volunteer = volunteer;
    }
}
