package al.adaction.demo.repository;

import al.adaction.demo.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {


    @Query("SELECT c FROM CollectionEntity c WHERE c.city.id = :cityId")
    List<CollectionEntity> findByCityId(@Param("cityId") Long cityId);

    List<CollectionEntity> findByStatus(String status);
}