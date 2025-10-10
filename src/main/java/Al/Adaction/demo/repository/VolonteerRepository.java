package al.adaction.demo.repository;

import al.adaction.demo.entity.VolonteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolonteerRepository extends JpaRepository<VolonteerEntity, Long> {

    List<VolonteerEntity> findByLocation(String location);
    List<VolonteerEntity> findByLastname(String lastname);
}
