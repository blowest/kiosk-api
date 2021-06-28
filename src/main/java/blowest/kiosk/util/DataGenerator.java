package blowest.kiosk.util;

import blowest.kiosk.entity.*;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.entity.status.TierStatus;
import blowest.kiosk.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
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

        menuRepository.save(Menu.construct("./images/xxx", "기네스와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, specialAndDiscount, burgerType));
        menuRepository.save(Menu.construct("./images/xxx", "몬스터와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, specialAndDiscount, burgerType));
        menuRepository.save(Menu.construct("./images/xxx", "치즈스틱", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, specialAndDiscount, burgerType));
        menuRepository.save(Menu.construct("./images/xxx", "콜라", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, specialAndDiscount, burgerType));

    }
}
