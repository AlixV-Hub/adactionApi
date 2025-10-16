package al.adaction.demo.service;

import al.adaction.demo.entity.VolunteerEntity;
import al.adaction.demo.repository.VolunteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Transactional
    public VolunteerEntity createVolunteer(VolunteerEntity volunteer) {
        if (volunteerRepository.existsByEmail(volunteer.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cet email est déjà utilisé"
            );
        }
        return volunteerRepository.save(volunteer);
    }

    public VolunteerEntity getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Volontaire introuvable avec l'ID: " + id
                ));
    }

    public List<VolunteerEntity> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Transactional
    public VolunteerEntity updateVolunteer(Long id, VolunteerEntity volunteer) {
        VolunteerEntity existing = getVolunteerById(id);

        if (!existing.getEmail().equals(volunteer.getEmail())
                && volunteerRepository.existsByEmail(volunteer.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cet email est déjà utilisé"
            );
        }

        existing.setFirstname(volunteer.getFirstname());
        existing.setLastname(volunteer.getLastname());
        existing.setEmail(volunteer.getEmail());
        existing.setLocation(volunteer.getLocation());

        return volunteerRepository.save(existing);
    }

    @Transactional
    public void deleteVolunteer(Long id) {
        VolunteerEntity volunteer = getVolunteerById(id);
        volunteerRepository.delete(volunteer);
    }
}