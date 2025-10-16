
package al.adaction.demo.repository;

import al.adaction.demo.entity.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {


    List<VolunteerEntity> findByLocation(String location);
    List<VolunteerEntity> findByLastname(String lastname);


    Optional<VolunteerEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}