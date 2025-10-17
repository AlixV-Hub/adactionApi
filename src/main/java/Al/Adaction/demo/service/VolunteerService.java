package al.adaction.demo.service;

import al.adaction.demo.entity.VolunteerEntity;
import al.adaction.demo.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    // Méthode d'authentification
    public VolunteerEntity authenticate(String email, String password) {
        List<VolunteerEntity> allVolunteers = volunteerRepository.findAll();

        for (VolunteerEntity volunteer : allVolunteers) {
            // Utiliser getPasswordInternal() pour accéder au password via réflexion
            String storedPassword = getPasswordFromVolunteer(volunteer);

            if (volunteer.getEmail() != null &&
                    volunteer.getEmail().equalsIgnoreCase(email) &&
                    storedPassword != null &&
                    storedPassword.equals(password)) {
                return volunteer;
            }
        }

        throw new RuntimeException("Identifiants incorrects");
    }

    // Méthode privée pour accéder au password via réflexion
    private String getPasswordFromVolunteer(VolunteerEntity volunteer) {
        try {
            java.lang.reflect.Field passwordField = VolunteerEntity.class.getDeclaredField("password");
            passwordField.setAccessible(true);
            return (String) passwordField.get(volunteer);
        } catch (Exception e) {
            System.err.println("⚠️ Erreur d'accès au password: " + e.getMessage());
            return null;
        }
    }

    public List<VolunteerEntity> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public VolunteerEntity getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Volontaire avec l'ID " + id + " non trouvé"
                ));
    }

    public VolunteerEntity createVolunteer(VolunteerEntity volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public VolunteerEntity updateVolunteer(Long id, VolunteerEntity updatedVolunteer) {
        VolunteerEntity existing = getVolunteerById(id);

        if (updatedVolunteer.getFirstname() != null) {
            existing.setFirstname(updatedVolunteer.getFirstname());
        }
        if (updatedVolunteer.getLastname() != null) {
            existing.setLastname(updatedVolunteer.getLastname());
        }
        if (updatedVolunteer.getLocation() != null) {
            existing.setLocation(updatedVolunteer.getLocation());
        }
        if (updatedVolunteer.getEmail() != null) {
            existing.setEmail(updatedVolunteer.getEmail());
        }

        return volunteerRepository.save(existing);
    }

    public void deleteVolunteer(Long id) {
        if (!volunteerRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Volontaire avec l'ID " + id + " non trouvé"
            );
        }
        volunteerRepository.deleteById(id);
    }
}