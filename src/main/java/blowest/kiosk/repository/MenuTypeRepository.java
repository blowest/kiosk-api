package blowest.kiosk.repository;

import blowest.kiosk.entity.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {

    @Query("select t from MenuType t where t.activationStatus = 'ACTIVATED'")
    List<MenuType> findAllActivated();

    @Query("select t from MenuType t where t.id = :id and t.activationStatus = 'ACTIVATED'")
    Optional<MenuType> findOneActivated(@Param("id") Long id);

    @Query("select t from MenuType t where t.id = :id and t.activationStatus = 'DEACTIVATED'")
    Optional<MenuType> findOneDeactivated(@Param("id") Long id);

}
