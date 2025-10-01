package Al.Adaction.demo.repository;

import Al.Adaction.demo.entity.VolonteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolonteerRepository extends JpaRepository<VolonteerEntity, Long> {
}
