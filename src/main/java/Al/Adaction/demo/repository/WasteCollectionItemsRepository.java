package al.adaction.demo.repository;

import al.adaction.demo.entity.WasteCollectionItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCollectionItemsRepository extends JpaRepository<WasteCollectionItemsEntity, Long> {}