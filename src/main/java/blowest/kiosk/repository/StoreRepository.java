package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s from Store s where s.activationStatus = 'ACTIVATED' order by s.id asc")
    List<Store> findAllActivated();

    @Query("select s from Store s where s.id = :id and s.activationStatus = 'ACTIVATED'")
    Optional<Store> findOneActivated(@Param("id") Long id);

    @Query("select s from Store s where s.id = :id and s.activationStatus = 'DEACTIVATED'")
    Optional<Store> findOneDeactivated(@Param("id") Long id);

}
