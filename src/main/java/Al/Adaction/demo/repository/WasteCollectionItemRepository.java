package al.adaction.demo.repository;

import al.adaction.demo.entity.WasteCollectionItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCollectionItemRepository extends JpaRepository<WasteCollectionItemEntity, Long> {}