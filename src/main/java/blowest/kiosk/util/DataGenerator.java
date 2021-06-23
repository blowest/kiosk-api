package blowest.kiosk.util;

import blowest.kiosk.entity.*;
import blowest.kiosk.entity.status.ActivationStatus;
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
        var burgerking = storeRepository.save(Store.createStore("버거킹", ActivationStatus.ACTIVATED));
        storeRepository.save(Store.createStore("맥도날드", ActivationStatus.ACTIVATED));
        storeRepository.save(Store.createStore("롯데리아", ActivationStatus.ACTIVATED));
        storeRepository.save(Store.createStore("맘스터치", ActivationStatus.ACTIVATED));

        var specialAndDiscount = topMenuRepository.save(TopMenu.createTopMenu("스페셜&할인팩", ActivationStatus.ACTIVATED, burgerking));
        var premium = topMenuRepository.save(TopMenu.createTopMenu("프리미엄", ActivationStatus.ACTIVATED, burgerking));
        var whopper = topMenuRepository.save(TopMenu.createTopMenu("와퍼", ActivationStatus.ACTIVATED, burgerking));
        var juniorAndBurger = topMenuRepository.save(TopMenu.createTopMenu("주니어&버거", ActivationStatus.ACTIVATED, burgerking));
        var alldaykingAndChicken = topMenuRepository.save(TopMenu.createTopMenu("올데이킹&치킨버거", ActivationStatus.ACTIVATED, burgerking));
        var side = topMenuRepository.save(TopMenu.createTopMenu("사이드", ActivationStatus.ACTIVATED, burgerking));
        var beverageAndDessert = topMenuRepository.save(TopMenu.createTopMenu("음료&디저트", ActivationStatus.ACTIVATED, burgerking));

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
