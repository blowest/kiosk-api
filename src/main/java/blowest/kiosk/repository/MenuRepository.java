package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

////    @Query("select m from Menu m where m.activationStatus = 'ACTIVATED'")
//    List<Menu> findAllByActivated();
//
////    @Query("select m from Menu m where and m.activationStatus = 'ACTIVATED'")
//    Optional<Menu> findByIdAndActivatedTrue(Long id);
//
//    Optional<Menu> findByIdAndActivatedFalse(Long id);

}
