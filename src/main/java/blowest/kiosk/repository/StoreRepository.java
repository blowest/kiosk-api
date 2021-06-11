package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAllByActivated(boolean activated);

    Optional<Store> findByIdAndActivated(Long id, boolean activated);

}
