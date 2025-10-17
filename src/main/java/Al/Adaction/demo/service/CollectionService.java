package al.adaction.demo.service;

import al.adaction.demo.entity.CollectionEntity;
import al.adaction.demo.entity.WasteCollectionItemEntity;
import al.adaction.demo.repository.CollectionRepository;
import al.adaction.demo.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CollectionService {

    private static final Logger logger = LoggerFactory.getLogger(CollectionService.class);

    private final CollectionRepository collectionRepository;
    private final CityRepository cityRepository;

    public CollectionService(
            CollectionRepository collectionRepository,
            CityRepository cityRepository
    ) {
        this.collectionRepository = collectionRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public CollectionEntity createCollection(CollectionEntity collection) {
        logger.debug("Création d'une nouvelle collecte pour la ville ID: {}",
                collection.getCity() != null ? collection.getCity().getId() : "null");

        validateNewCollection(collection);

        if (collection.getWasteCollectionItems() != null && !collection.getWasteCollectionItems().isEmpty()) {
            logger.debug("Nombre d'items à ajouter: {}", collection.getWasteCollectionItems().size());

            List<WasteCollectionItemEntity> itemsCopy = new ArrayList<>(collection.getWasteCollectionItems());

            collection.getWasteCollectionItems().clear();

            itemsCopy.forEach(item -> {
                if (item != null && item.getWasteType() != null) {
                    collection.addWasteCollectionItem(item);
                    logger.debug("Item ajouté - WasteType ID: {}, Quantité: {} kg",
                            item.getWasteType().getId(), item.getQuantity());
                }
            });
        }

        CollectionEntity saved = collectionRepository.save(collection);

        logger.info("Collecte créée avec succès - ID: {}", saved.getId());
        return saved;
    }

    public CollectionEntity getCollectionById(Long id) {
        logger.debug("Récupération de la collecte ID: {}", id);
        return collectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Collecte introuvable avec l'ID: " + id
                ));
    }

    public List<CollectionEntity> getAllCollections() {
        logger.debug("Récupération de toutes les collectes");
        return collectionRepository.findAll();
    }

    @Transactional
    public CollectionEntity updateCollection(Long id, CollectionEntity updated) {
        logger.debug("Mise à jour de la collecte ID: {}", id);

        CollectionEntity existing = getCollectionById(id);

        existing.setCollectionDate(updated.getCollectionDate());
        existing.setStatus(updated.getStatus());

        if (updated.getCity() != null) {
            if (!cityRepository.existsById(updated.getCity().getId())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Ville introuvable avec l'ID: " + updated.getCity().getId()
                );
            }
            existing.setCity(updated.getCity());
        }

        if (updated.getWasteCollectionItems() != null) {
            List<WasteCollectionItemEntity> itemsToRemove = List.copyOf(existing.getWasteCollectionItems());
            itemsToRemove.forEach(existing::removeWasteCollectionItem);

            updated.getWasteCollectionItems().forEach(item -> {
                if (item != null && item.getWasteType() != null) {
                    existing.addWasteCollectionItem(item);
                }
            });

            logger.debug("Items mis à jour - Nouveau nombre: {}", existing.getWasteCollectionItems().size());
        }

        CollectionEntity saved = collectionRepository.save(existing);

        logger.info("Collecte mise à jour avec succès - ID: {}", saved.getId());
        return saved;
    }

    @Transactional
    public void deleteCollection(Long id) {
        logger.debug("Suppression de la collecte ID: {}", id);

        CollectionEntity collection = getCollectionById(id);

        if (collection.isInProgress()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Impossible de supprimer une collecte en cours"
            );
        }

        collectionRepository.delete(collection);
        logger.info("Collecte supprimée avec succès - ID: {}", id);
    }

    public List<CollectionEntity> getCollectionsByCity(Long cityId) {
        logger.debug("Récupération des collectes pour la ville ID: {}", cityId);
        return collectionRepository.findByCityId(cityId);
    }

    public List<CollectionEntity> getCollectionsByStatus(String status) {
        logger.debug("Récupération des collectes avec le statut: {}", status);
        return collectionRepository.findByStatus(status);
    }

    @Transactional
    public void startCollection(Long id) {
        logger.debug("Démarrage de la collecte ID: {}", id);
        CollectionEntity collection = getCollectionById(id);
        collection.start();
        collectionRepository.save(collection);
        logger.info("Collecte démarrée - ID: {}", id);
    }

    @Transactional
    public void completeCollection(Long id) {
        logger.debug("Complétion de la collecte ID: {}", id);
        CollectionEntity collection = getCollectionById(id);
        collection.complete();
        collectionRepository.save(collection);
        logger.info("Collecte terminée - ID: {}", id);
    }

    @Transactional
    public void cancelCollection(Long id) {
        logger.debug("Annulation de la collecte ID: {}", id);
        CollectionEntity collection = getCollectionById(id);
        collection.cancel();
        collectionRepository.save(collection);
        logger.info("Collecte annulée - ID: {}", id);
    }

    @Transactional
    public void addItemToCollection(Long collectionId, WasteCollectionItemEntity item) {
        logger.debug("Ajout d'un item à la collecte ID: {}", collectionId);

        CollectionEntity collection = getCollectionById(collectionId);

        if (!collection.isPlanned()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Impossible d'ajouter un item à une collecte déjà démarrée"
            );
        }

        collection.addWasteCollectionItem(item);
        collectionRepository.save(collection);

        logger.info("Item ajouté à la collecte ID: {}", collectionId);
    }

    private void validateNewCollection(CollectionEntity collection) {

        if (collection.getCity() == null || collection.getCity().getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La ville est obligatoire"
            );
        }

        if (!cityRepository.existsById(collection.getCity().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ville introuvable avec l'ID: " + collection.getCity().getId()
            );
        }

        if (collection.getCollectionDate() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La date de collecte est obligatoire"
            );
        }

        if (collection.getWasteCollectionItems() != null) {
            collection.getWasteCollectionItems().forEach(item -> {
                if (item.getWasteType() == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Chaque item doit avoir un type de déchet"
                    );
                }
                if (item.getQuantity() == null || item.getQuantity() < 0) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "La quantité ne peut pas être négative"
                    );
                }
            });
        }
    }

    public List<CollectionEntity> findAll() {
        return collectionRepository.findAll();
    }

    public Optional<CollectionEntity> findById(Long id) {
        return collectionRepository.findById(id);
    }

    @Transactional
    public CollectionEntity save(CollectionEntity collection) {
        return collectionRepository.save(collection);
    }

    @Transactional
    public CollectionEntity update(Long id, CollectionEntity updatedCollection) {
        return updateCollection(id, updatedCollection);
    }

    public boolean existsById(Long id) {
        return collectionRepository.existsById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        collectionRepository.deleteById(id);
    }
}