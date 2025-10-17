package al.adaction.demo.controller;

import al.adaction.demo.dto.LoginRequest;
import al.adaction.demo.entity.VolunteerEntity;
import al.adaction.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final VolunteerService volunteerService;

    @Autowired
    public AuthController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔍 Tentative de connexion pour: " + loginRequest.getEmail());

        try {
            VolunteerEntity volunteer = volunteerService.authenticate(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );

            System.out.println("✅ Connexion réussie: " + volunteer.getFirstname() + " " + volunteer.getLastname());
            return ResponseEntity.ok(volunteer);

        } catch (RuntimeException e) {
            System.out.println("❌ Échec de connexion: " + e.getMessage());

            Map<String, String> error = new HashMap<>();
            error.put("message", "Email ou mot de passe incorrect");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}