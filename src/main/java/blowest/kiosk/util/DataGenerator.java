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
        var burgerking = storeRepository.save(Store.construct("버거킹", ActivationStatus.ACTIVATED));
        storeRepository.save(Store.construct("맥도날드", ActivationStatus.ACTIVATED));
        storeRepository.save(Store.construct("롯데리아", ActivationStatus.ACTIVATED));
        storeRepository.save(Store.construct("맘스터치", ActivationStatus.ACTIVATED));

        var specialAndDiscount = topMenuRepository.save(TopMenu.construct("스페셜&할인팩", ActivationStatus.ACTIVATED, burgerking));
        var premium = topMenuRepository.save(TopMenu.construct("프리미엄", ActivationStatus.ACTIVATED, burgerking));
        var whopper = topMenuRepository.save(TopMenu.construct("와퍼", ActivationStatus.ACTIVATED, burgerking));
        var juniorAndBurger = topMenuRepository.save(TopMenu.construct("주니어&버거", ActivationStatus.ACTIVATED, burgerking));
        var alldaykingAndChicken = topMenuRepository.save(TopMenu.construct("올데이킹&치킨버거", ActivationStatus.ACTIVATED, burgerking));
        var side = topMenuRepository.save(TopMenu.construct("사이드", ActivationStatus.ACTIVATED, burgerking));
        var beverageAndDessert = topMenuRepository.save(TopMenu.construct("음료&디저트", ActivationStatus.ACTIVATED, burgerking));

        var burgerType = menuTypeRepository.save(MenuType.construct("버거", ActivationStatus.ACTIVATED));
        var sideType = menuTypeRepository.save(MenuType.construct("사이드", ActivationStatus.ACTIVATED));
        var beverageType = menuTypeRepository.save(MenuType.construct("음료", ActivationStatus.ACTIVATED));

        var guinnessWhopper = menuRepository.save(Menu.construct("./images/xxx", false, 2000, ActivationStatus.ACTIVATED, specialAndDiscount, burgerType));
        var monsterWhopper = menuRepository.save(Menu.construct("./images/xxx", false, 2000, ActivationStatus.ACTIVATED, specialAndDiscount, burgerType));
        var cheeseStick = menuRepository.save(Menu.construct("./images/xxx", false, 2000, ActivationStatus.ACTIVATED, specialAndDiscount, sideType));
        var coke =  menuRepository.save(Menu.construct("./images/xxx", false, 2000, ActivationStatus.ACTIVATED, specialAndDiscount, beverageType));

        menuDetailRepository.save(MenuDetail.construct("기네스와퍼", 8500, "./images/xxx", ActivationStatus.ACTIVATED, guinnessWhopper));
        menuDetailRepository.save(MenuDetail.construct("몬스터와퍼", 8500,"./images/xxx",ActivationStatus.ACTIVATED, monsterWhopper));
        menuDetailRepository.save(MenuDetail.construct("치즈스틱",2200, "./images/xxx",ActivationStatus.ACTIVATED, cheeseStick));
        menuDetailRepository.save(MenuDetail.construct("콜라", 2000, "./images/xxx", ActivationStatus.ACTIVATED, coke));
    }
}
