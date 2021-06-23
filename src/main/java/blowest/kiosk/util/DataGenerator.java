package blowest.kiosk.util;

import blowest.kiosk.entity.*;
import blowest.kiosk.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements ApplicationRunner {

    private final StoreRepository storeRepository;

    private final TopMenuRepository topMenuRepository;

    private final MenuTypeRepository menuTypeRepository;

    private final MenuRepository menuRepository;

    private final MenuDetailRepository menuDetailRepository;

    public DataGenerator(StoreRepository storeRepository, TopMenuRepository topMenuRepository, MenuTypeRepository menuTypeRepository, MenuRepository menuRepository, MenuDetailRepository menuDetailRepository) {
        this.storeRepository = storeRepository;
        this.topMenuRepository = topMenuRepository;
        this.menuTypeRepository = menuTypeRepository;
        this.menuRepository = menuRepository;
        this.menuDetailRepository = menuDetailRepository;
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

        var guinnessWhopper = menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, burgerType));
        var monsterWhopper = menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, burgerType));
        var cheeseStick = menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, sideType));
        var coke =  menuRepository.save(new Menu("./images/xxx", false, 2000, true, specialAndDiscount, beverageType));

        menuDetailRepository.save(new MenuDetail("기네스와퍼", 8500, "./images/xxx", true, guinnessWhopper));
        menuDetailRepository.save(new MenuDetail("몬스터와퍼", 8500,"./images/xxx",true, monsterWhopper));
        menuDetailRepository.save(new MenuDetail("치즈스틱",2200, "./images/xxx",true, cheeseStick));
        menuDetailRepository.save(new MenuDetail("콜라", 2000, "./images/xxx", true, coke));
    }
}
