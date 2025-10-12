package al.adaction.demo.repository;

import al.adaction.demo.entity.CollectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectsRepository extends JpaRepository<CollectsEntity, Long> {}
