package al.adaction.demo.repository;

import al.adaction.demo.entity.VolonteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolonteerRepository extends JpaRepository<VolonteerEntity, Long> {
}
