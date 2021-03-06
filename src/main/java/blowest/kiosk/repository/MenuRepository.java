package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select m from Menu m where m.activationStatus = 'ACTIVATED' order by m.id asc")
    List<Menu> findAllActivated();

    @Query("select m from Menu m where m.id = :id and m.activationStatus = 'ACTIVATED'")
    Optional<Menu> findOneActivated(@Param("id") Long id);

    @Query("select m from Menu m where m.id = :id and m.activationStatus = 'DEACTIVATED'")
    Optional<Menu> findOneDeactivated(@Param("id") Long id);

    @Query(value = "select count(m.id) from Menu m where m.topMenu.id = :topMenuId and m.activationStatus = 'ACTIVATED'")
    Optional<Long> countMenusByTopMenuId(@Param("topMenuId") Long topMenuId);

    @Query(value = "select m from Menu m where m.topMenu.id = :topMenuId and m.activationStatus = 'ACTIVATED'",
            countQuery = "select count(m.id) from Menu m where m.topMenu.id = :topMenuId and m.activationStatus = 'ACTIVATED'")
    Page<Menu> findMenusByTopMenuId(@Param("topMenuId") Long topMenuId, Pageable pageable);
}
