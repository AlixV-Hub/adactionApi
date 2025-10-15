package al.adaction.demo.controller.collects;

import al.adaction.demo.entity.CollectsEntity;
import al.adaction.demo.service.collects.ICollectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collects")
public class CollectsController {

    private final ICollectService collectService;

    public CollectsController(ICollectService collectService) {
        this.collectService = collectService;
    }

    @GetMapping
    public List<CollectsEntity> getAllCollects() {
        return collectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectsEntity> getCollectById(@PathVariable Long id) {
        Optional<CollectsEntity> collect = collectService.findById(id);
        return collect.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCollect(@RequestBody CollectsEntity collect) {
        try {
            System.out.println("📥 Réception collecte:");
            System.out.println("   Date: " + collect.getCollectionDate());
            System.out.println("   CityId: " + collect.getCityId());
            System.out.println("   Items: " + (collect.getWasteCollectionItems() != null
                    ? collect.getWasteCollectionItems().size() : 0));

            // ✅ Utiliser save() et non createCollect()
            CollectsEntity saved = collectService.save(collect);

            System.out.println("✅ Collecte sauvegardée avec ID: " + saved.getId());
            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la création: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erreur: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectsEntity> updateCollect(
            @PathVariable Long id,
            @RequestBody CollectsEntity updatedCollect) {
        CollectsEntity updated = collectService.update(id, updatedCollect);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollect(@PathVariable Long id) {
        if (collectService.existsById(id)) {
            collectService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Classe interne pour les réponses d'erreur JSON
    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}