package blowest.kiosk.util;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.repository.MenuRepository;
import blowest.kiosk.repository.MenuTypeRepository;
import blowest.kiosk.repository.StoreRepository;
import blowest.kiosk.repository.TopMenuRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements ApplicationRunner {

    private final StoreRepository storeRepository;

    private final TopMenuRepository topMenuRepository;

    private final MenuTypeRepository menuTypeRepository;

    private final MenuRepository menuRepository;

    public DataGenerator(StoreRepository storeRepository, TopMenuRepository topMenuRepository, MenuTypeRepository menuTypeRepository, MenuRepository menuRepository) {
        this.storeRepository = storeRepository;
        this.topMenuRepository = topMenuRepository;
        this.menuTypeRepository = menuTypeRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        var burgerking = storeRepository.save(new Store("버거킹", true));
        storeRepository.save(new Store("맥도날드", true));
        storeRepository.save(new Store("롯데리아", true));
        storeRepository.save(new Store("맘스터치", true));

        var specialAndDiscount = topMenuRepository.save(new TopMenu("스페셜&할인팩", true, burgerking));
        var premium = topMenuRepository.save(new TopMenu("프리미엄", true, burgerking));
        var whopper = topMenuRepository.save(new TopMenu("와퍼", true, burgerking));
        var juniorAndBurger = topMenuRepository.save(new TopMenu("주니어&버거", true, burgerking));
        var alldaykingAndChicken = topMenuRepository.save(new TopMenu("올데이킹&치킨버거", true, burgerking));
        var side = topMenuRepository.save(new TopMenu("사이드", true, burgerking));
        var beverageAndDessert = topMenuRepository.save(new TopMenu("음료&디저트", true, burgerking));

        var burgerType = menuTypeRepository.save(new MenuType("버거", true));
        var sideType = menuTypeRepository.save(new MenuType("사이드", true));
        var beverageType = menuTypeRepository.save(new MenuType("음료", true));

        menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, burgerType));
        menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, burgerType));
        menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, sideType));
        menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, beverageType));
    }
}
