package al.adaction.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "volonteers")
public class VolunteerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "location")
    private String location;

    @Column(name = "email")
    private String email;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "password")
    private String password;

    // Constructeur vide (obligatoire pour JPA)
    public VolunteerEntity() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}