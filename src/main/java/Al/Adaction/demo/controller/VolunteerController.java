package al.adaction.demo.controller;

import al.adaction.demo.entity.VolunteerEntity;
import al.adaction.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "http://localhost:4200")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public ResponseEntity<List<VolunteerEntity>> getAllVolunteers() {
        List<VolunteerEntity> volunteers = volunteerService.getAllVolunteers();
        return ResponseEntity.ok(volunteers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerEntity> getVolunteerById(@PathVariable Long id) {
        // Le service lance déjà une exception si non trouvé (ResponseStatusException)
        // Spring gère automatiquement le code 404
        VolunteerEntity volunteer = volunteerService.getVolunteerById(id);
        return ResponseEntity.ok(volunteer);
    }

    @PostMapping
    public ResponseEntity<VolunteerEntity> createVolunteer(@RequestBody VolunteerEntity volunteer) {
        VolunteerEntity created = volunteerService.createVolunteer(volunteer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerEntity> updateVolunteer(
            @PathVariable Long id,
            @RequestBody VolunteerEntity updatedVolunteer
    ) {
        VolunteerEntity volunteer = volunteerService.updateVolunteer(id, updatedVolunteer);
        return ResponseEntity.ok(volunteer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return ResponseEntity.noContent().build();
    }
}