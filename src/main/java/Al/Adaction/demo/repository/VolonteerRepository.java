package al.adaction.demo.repository;

import al.adaction.demo.entity.VolonteerEntity;
import jakarta.websocket.Decoder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolonteerRepository extends JpaRepository<VolonteerEntity, Long> {

    List<VolonteerEntity> findByLocation(String location);
    List<VolonteerEntity> findByLastname(String lastname);
}
