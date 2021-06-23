package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.TopMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopMenuRepository extends JpaRepository<TopMenu, Long> {

    @Query("select t from TopMenu t where t.activationStatus = 'ACTIVATED'")
    List<TopMenu> findAllActivated();

    @Query("select t from TopMenu t where t.id = :id and t.activationStatus = 'ACTIVATED'")
    Optional<TopMenu> findOneActivated(@Param("id") Long id);

    @Query("select t from TopMenu t where t.id = :id and t.activationStatus = 'DEACTIVATED'")
    Optional<TopMenu> findOneDeactivated(@Param("id") Long id);
}
