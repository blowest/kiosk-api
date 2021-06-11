package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAllByActivatedTrue();

    Optional<Store> findByIdAndActivatedTrue(Long id);

    Optional<Store> findByIdAndActivatedFalse(Long id);

}
