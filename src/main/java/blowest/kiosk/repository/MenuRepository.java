package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByActivatedTrue();

    Optional<Menu> findByIdAndActivatedTrue(Long id);

    Optional<Menu> findByIdAndActivatedFalse(Long id);

}
