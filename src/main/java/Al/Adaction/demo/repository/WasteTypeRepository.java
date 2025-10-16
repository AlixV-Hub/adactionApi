package al.adaction.demo.repository;

import al.adaction.demo.entity.WasteTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteTypeRepository extends JpaRepository<WasteTypesEntity, Long> {}