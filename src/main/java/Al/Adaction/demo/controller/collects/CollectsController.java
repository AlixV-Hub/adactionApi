package al.adaction.demo.controller.collects;

import al.adaction.demo.entity.CollectsEntity;
import al.adaction.demo.service.collects.ICollectsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collects")
public class CollectsController {

    private final ICollectsService collectsService;

    public CollectsController(ICollectsService collectsService) {
        this.collectsService = collectsService;
    }

    @GetMapping
    public List<CollectsEntity> getAllCollects() {
        return collectsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectsEntity> getCollectById(@PathVariable Long id) {
        Optional<CollectsEntity> collect = collectsService.findById(id);
        return collect.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CollectsEntity createCollect(@RequestBody CollectsEntity collect) {
        return collectsService.save(collect);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectsEntity> updateCollect(@PathVariable Long id, @RequestBody CollectsEntity updatedCollect) {
        CollectsEntity updated = collectsService.update(id, updatedCollect);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollect(@PathVariable Long id) {
        if (collectsService.existsById(id)) {
            collectsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
