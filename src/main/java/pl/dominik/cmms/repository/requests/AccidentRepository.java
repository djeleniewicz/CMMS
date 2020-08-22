package pl.dominik.cmms.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.requests.Accident;

import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {


    Accident findById(Long id);

    @Query(value = "SELECT a.title,a.note FROM accidents a WHERE a.reported_by_id=:id", nativeQuery = true)
    List<Accident> findAllByCreatedBy(@Param("id") Long reported_by_id);
}
