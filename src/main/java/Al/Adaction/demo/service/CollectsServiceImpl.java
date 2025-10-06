package al.adaction.demo.service.collects;

import al.adaction.demo.entity.CollectsEntity;
import al.adaction.demo.repository.CollectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CollectsServiceImpl implements ICollectsService {

    @Autowired
    private CollectsRepository collectsRepository;

    @Override
    public List<CollectsEntity> findAll() {
        return collectsRepository.findAll();
    }

    @Override
    public Optional<CollectsEntity> findById(Long id) {
        return collectsRepository.findById(id);
    }

    @Override
    public CollectsEntity save(CollectsEntity collect) {
        return collectsRepository.save(collect);
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
    public CollectsEntity update(Long id, CollectsEntity updatedCollect) {
        return collectsRepository.findById(id).map(existingCollect -> {
            existingCollect.setCollectionDate(updatedCollect.getCollectionDate());
            existingCollect.setCityId(updatedCollect.getCityId());
            return collectsRepository.save(existingCollect);
        }).orElse(null);
    }
}
