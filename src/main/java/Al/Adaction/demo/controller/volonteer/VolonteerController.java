package al.adaction.demo.controller.volonteer;

import al.adaction.demo.entity.VolonteerEntity;
import al.adaction.demo.service.IVolonteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volonteers")
@CrossOrigin(origins = "http://localhost:4200")
public class VolonteerController {

    private final IVolonteerService volonteerService;

    @Autowired
    public VolonteerController(IVolonteerService volonteerService) {
        this.volonteerService = volonteerService;
    }

    // GET /api/volonteers → récupérer tous les volontaires
    @GetMapping
    public List<VolonteerEntity> getAllVolonteers() {
        return volonteerService.getAllVolonteers();
    }

    // GET /api/volonteers/{id} → récupérer un volontaire par ID
    @GetMapping("/{id}")
    public ResponseEntity<VolonteerEntity> getVolonteerById(@PathVariable Long id) {
        VolonteerEntity volonteer = volonteerService.getVolonteerById(id);
        if (volonteer != null) {
            return ResponseEntity.ok(volonteer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/volonteers → créer un volontaire
    @PostMapping
    public ResponseEntity<VolonteerEntity> createVolonteer(@RequestBody VolonteerEntity volonteer) {
        VolonteerEntity created = volonteerService.createVolonteer(volonteer);
        return ResponseEntity.ok(created);
    }

    // PUT /api/volonteers/{id} → mettre à jour un volontaire
    @PutMapping("/{id}")
    public ResponseEntity<VolonteerEntity> updateVolonteer(@PathVariable Long id, @RequestBody VolonteerEntity updatedVolonteer) {
        VolonteerEntity volonteer = volonteerService.updateVolonteer(id, updatedVolonteer);
        if (volonteer != null) {
            return ResponseEntity.ok(volonteer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/volonteers/{id} → supprimer un volontaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolonteer(@PathVariable Long id) {
        boolean deleted = volonteerService.deleteVolonteer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
