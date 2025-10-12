package al.adaction.demo.service.collects;

import al.adaction.demo.entity.CollectsEntity;
import al.adaction.demo.entity.WasteCollectionItemsEntity;
import al.adaction.demo.repository.CollectsRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CollectsServiceImpl implements ICollectsService {

    private final CollectsRepository collectsRepository;

    public CollectsServiceImpl(CollectsRepository collectsRepository) {
        this.collectsRepository = collectsRepository;
    }

    @Override
    public CollectsEntity save(CollectsEntity collect) {
        collect.setCreatedAt(LocalDateTime.now());
        collect.setUpdatedAt(LocalDateTime.now());

        // Lien bidirectionnel pour tous les items, même quantité = 0
        if (collect.getWasteCollectionItems() != null) {
            collect.getWasteCollectionItems().stream()
                    .filter(item -> item != null && item.getWasteType() != null)
                    .forEach(item -> item.setCollection(collect));
        }

        return collectsRepository.save(collect);
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
                updated.getWasteCollectionItems().stream()
                        .filter(item -> item != null && item.getWasteType() != null)
                        .forEach(item -> item.setCollection(existing));
                existing.setWasteCollectionItems(updated.getWasteCollectionItems());
            }

            return collectsRepository.save(existing);
        }).orElse(null);
    }
}
