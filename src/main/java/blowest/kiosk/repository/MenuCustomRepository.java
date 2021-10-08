package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.status.TierStatus;

import java.util.List;

public interface MenuCustomRepository {
    List<Menu> findAllBest(TierStatus tierStatus);
}
