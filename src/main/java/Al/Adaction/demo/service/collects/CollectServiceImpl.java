package al.adaction.demo.service.collects;

import al.adaction.demo.entity.CollectsEntity;
import al.adaction.demo.repository.CollectsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CollectServiceImpl implements ICollectService {

    private final CollectsRepository collectsRepository;

    public CollectServiceImpl(CollectsRepository collectsRepository) {
        this.collectsRepository = collectsRepository;
    }

    @Override
    public CollectsEntity save(CollectsEntity collect) {
        System.out.println("💾 Sauvegarde collecte:");
        System.out.println("   Date: " + collect.getCollectionDate());
        System.out.println("   CityId: " + collect.getCityId());

        if (collect.getCreatedAt() == null) {
            collect.setCreatedAt(LocalDateTime.now());
        }
        collect.setUpdatedAt(LocalDateTime.now());

        if (collect.getWasteCollectionItems() != null) {
            System.out.println("   Nombre d'items: " + collect.getWasteCollectionItems().size());
            collect.getWasteCollectionItems().forEach(item -> {
                if (item != null && item.getWasteType() != null) {
                    item.setCollection(collect);
                    System.out.println("      - WasteType " + item.getWasteType().getId() + ": " + item.getQuantity() + " kg");
                }
            });
        }

        try {
            CollectsEntity saved = collectsRepository.save(collect);
            System.out.println("✅ Collecte sauvegardée avec ID: " + saved.getId());
            return saved;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la sauvegarde: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur sauvegarde: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CollectsEntity> findAll() {
        return collectsRepository.findAll();
    }

    @Override
    public Optional<CollectsEntity> findById(Long id) {
        return collectsRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        collectsRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return collectsRepository.existsById(id);
    }

    @Override
    public CollectsEntity update(Long id, CollectsEntity updated) {
        return collectsRepository.findById(id).map(existing -> {
            existing.setCollectionDate(updated.getCollectionDate());
            existing.setCityId(updated.getCityId());
            existing.setUpdatedAt(LocalDateTime.now());

            if (updated.getWasteCollectionItems() != null) {
                if (existing.getWasteCollectionItems() != null) {
                    existing.getWasteCollectionItems().clear();
                }

                updated.getWasteCollectionItems().forEach(item -> {
                    if (item != null && item.getWasteType() != null) {
                        item.setCollection(existing);
                        if (existing.getWasteCollectionItems() != null) {
                            existing.getWasteCollectionItems().add(item);
                        }
                    }
                });
            }

            return collectsRepository.save(existing);
        }).orElse(null);
    }
}