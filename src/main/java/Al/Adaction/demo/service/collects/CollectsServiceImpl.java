package al.adaction.demo.service.collects;
import al.adaction.demo.entity.WasteCollectionItemsEntity;
import al.adaction.demo.entity.WasteTypesEntity;
import al.adaction.demo.repository.WasteCollectionItemsRepository;
import al.adaction.demo.repository.WasteTypesRepository;
import al.adaction.demo.entity.CollectsEntity;
import al.adaction.demo.repository.CollectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Override
    public CollectsEntity save(CollectsEntity collect) {
        // Mettre les dates par défaut si elles ne sont pas fournies
        if (collect.getCollectionDate() == null) {
            collect.setCollectionDate(LocalDate.now());
        }
        if (collect.getCreatedAt() == null) {
            collect.setCreatedAt(LocalDateTime.now());
        }
        collect.setUpdatedAt(LocalDateTime.now());

        // Sauvegarde de la collecte principale
        CollectsEntity savedCollect = collectsRepository.save(collect);

        // Récupération de tous les types de déchets
        List<WasteTypesEntity> allWasteTypes = wasteTypesRepository.findAll();

        // Création d’un WasteCollectionItemsEntity pour chaque type de déchet
        for (WasteTypesEntity wasteType : allWasteTypes) {
            WasteCollectionItemsEntity item = new WasteCollectionItemsEntity();
            item.setCollection(savedCollect);
            item.setWasteType(wasteType);
            item.setQuantity(0.0); // quantité par défaut
            wasteCollectionItemsRepository.save(item);
        }

        return savedCollect;
    }


    @Autowired
    private WasteCollectionItemsRepository wasteCollectionItemsRepository;

    @Autowired
    private WasteTypesRepository wasteTypesRepository;

}
