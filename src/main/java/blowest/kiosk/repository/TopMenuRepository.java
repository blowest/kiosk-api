package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.TopMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopMenuRepository extends JpaRepository<TopMenu, Long> {

    List<TopMenu> findAllByActivatedTrue();

    Optional<TopMenu> findByIdAndActivatedTrue(Long id);

    Optional<TopMenu> findByIdAndActivatedFalse(Long id);
}
