package al.adaction.demo.service.collects;

import al.adaction.demo.entity.CollectsEntity;
import java.util.List;
import java.util.Optional;

public interface ICollectService {
    List<CollectsEntity> findAll();
    Optional<CollectsEntity> findById(Long id);
    CollectsEntity save(CollectsEntity collect);
    void deleteById(Long id);
    boolean existsById(Long id);
    CollectsEntity update(Long id, CollectsEntity updatedCollect);
}