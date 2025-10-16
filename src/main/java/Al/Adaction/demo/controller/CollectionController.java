package al.adaction.demo.controller;

import al.adaction.demo.entity.CollectionEntity;
import al.adaction.demo.service.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collections")
@CrossOrigin(origins = "http://localhost:4200")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {  // ← Changé
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<CollectionEntity> getAllCollections() {
        return collectionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionEntity> getCollectionById(@PathVariable Long id) {
        Optional<CollectionEntity> collection = collectionService.findById(id);
        return collection.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "*/*")
    public ResponseEntity<?> createCollection(@RequestBody String jsonBody) {
        try {
            System.out.println("📥 JSON reçu brut:");
            System.out.println(jsonBody);

            // Parser manuellement le JSON
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

            CollectionEntity collection = mapper.readValue(jsonBody, CollectionEntity.class);

            System.out.println("📥 Après parsing:");
            System.out.println("   Date: " + collection.getCollectionDate());
            // ✅ CORRECTION ICI - utilisez la méthode getCityId() que nous avons ajoutée
            System.out.println("   CityId: " + (collection.getCity() != null ? collection.getCity().getId() : "null"));
            System.out.println("   Items: " + (collection.getWasteCollectionItems() != null
                    ? collection.getWasteCollectionItems().size() : 0));

            CollectionEntity saved = collectionService.save(collection);

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
    public ResponseEntity<CollectionEntity> updateCollection(
            @PathVariable Long id,
            @RequestBody CollectionEntity updatedCollection) {
        CollectionEntity updated = collectionService.update(id, updatedCollection);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id) {
        if (collectionService.existsById(id)) {
            collectionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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