package blowest.kiosk.repository;

import blowest.kiosk.entity.MenuDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDetailRepository extends JpaRepository<MenuDetail, Long> {

    List<MenuDetail> findAllByActivatedTrue();

    Optional<MenuDetail> findByIdAndActivatedTrue(Long id);

    Optional<MenuDetail> findByIdAndActivatedFalse(Long id);
}
