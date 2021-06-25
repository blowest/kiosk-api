package blowest.kiosk.repository;

import blowest.kiosk.entity.MenuDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuDetailRepository extends JpaRepository<MenuDetail, Long> {

    @Query("select d from MenuDetail d where d.activationStatus = 'ACTIVATED'")
    List<MenuDetail> findAllActivated();

    @Query("select d from MenuDetail d where d.id = :id and d.activationStatus = 'ACTIVATED'")
    Optional<MenuDetail> findOneActivated(@Param("id") Long id);

    @Query("select d from MenuDetail d where d.id = :id and d.activationStatus = 'DEACTIVATED'")
    Optional<MenuDetail> findOneDeactivated(@Param("id") Long id);
}
