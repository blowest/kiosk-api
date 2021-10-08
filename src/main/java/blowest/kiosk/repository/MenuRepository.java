package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> , MenuCustomRepository{

    @Query("select m from Menu m where m.activationStatus = 'ACTIVATED' order by m.id asc")
    List<Menu> findAllActivated();

    @Query("select m from Menu m where m.id = :id and m.activationStatus = 'ACTIVATED'")
    Optional<Menu> findOneActivated(@Param("id") Long id);

    @Query("select m from Menu m where m.id = :id and m.activationStatus = 'DEACTIVATED'")
    Optional<Menu> findOneDeactivated(@Param("id") Long id);


//    @Query("select m from Menu m where m.tierStatus = 'BEST' order by m.id asc")
//    List<Menu> findAllBest();

}
