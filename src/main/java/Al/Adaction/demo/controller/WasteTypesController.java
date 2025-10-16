package al.adaction.demo.controller;

import al.adaction.demo.entity.WasteTypesEntity;
import al.adaction.demo.service.wastetypes.IWasteTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-types")
public class WasteTypesController {

    private final IWasteTypesService wasteTypesService;

    @Autowired
    public WasteTypesController(IWasteTypesService wasteTypesService) {
        this.wasteTypesService = wasteTypesService;
    }

    @GetMapping
    public List<WasteTypesEntity> getAll() {
        return wasteTypesService.getAllWasteTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteTypesEntity> getById(@PathVariable Long id) {
        return wasteTypesService.getWasteTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WasteTypesEntity create(@RequestBody WasteTypesEntity wasteType) {
        return wasteTypesService.createWasteType(wasteType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteTypesEntity> update(@PathVariable Long id, @RequestBody WasteTypesEntity wasteType) {
        WasteTypesEntity updated = wasteTypesService.updateWasteType(id, wasteType);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        wasteTypesService.deleteWasteType(id);
    }
}
