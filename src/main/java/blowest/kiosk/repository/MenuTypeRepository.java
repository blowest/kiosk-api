package blowest.kiosk.repository;

import blowest.kiosk.entity.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {

    List<MenuType> findAllByActivatedTrue();

    Optional<MenuType> findByIdAndActivatedTrue(Long id);

    Optional<MenuType> findByIdAndActivatedFalse(Long id);

}
