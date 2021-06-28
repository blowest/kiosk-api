package blowest.kiosk.util;

import blowest.kiosk.entity.*;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.entity.status.MenuType;
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

    private final MenuRepository menuRepository;

    public DataGenerator(StoreRepository storeRepository, TopMenuRepository topMenuRepository, MenuRepository menuRepository) {
        this.storeRepository = storeRepository;
        this.topMenuRepository = topMenuRepository;
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
        var alldaykingAndChicken = topMenuRepository.save(TopMenu.construct("올데이킹&치킨", ActivationStatus.ACTIVATED, burgerking));
        var side = topMenuRepository.save(TopMenu.construct("사이드", ActivationStatus.ACTIVATED, burgerking));
        var beverageAndDessert = topMenuRepository.save(TopMenu.construct("음료&디저트", ActivationStatus.ACTIVATED, burgerking));

        menuRepository.save(Menu.construct("./images/xxx", "몬스터X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "통새우X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "콰트로치즈X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "기네스통베이컨와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "기네스콰트로치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "기네스머쉬룸와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "기네스와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "몬스터와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "콰트로치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "통새우와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));

        menuRepository.save(Menu.construct("./images/xxx", "통베이컨와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "스태커2와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "스태커3와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "스태커4와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "리얼 와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "베이컨치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, whopper, MenuType.BURGER));

        menuRepository.save(Menu.construct("./images/xxx", "아기상어새우버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "엄마상어새우버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "아빠상어새우버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "고추장 소불고기버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "고추장 버섯불고기버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "직화소불고기버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "통새우와퍼주니어", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "콰트로치즈와퍼주니어", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "와퍼주니어", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "치즈와퍼주니어", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "치즈버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, juniorAndBurger, MenuType.BURGER));

        menuRepository.save(Menu.construct("./images/xxx", "바비큐킹치킨버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, alldaykingAndChicken, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "킹치킨버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, alldaykingAndChicken, MenuType.BURGER));
        menuRepository.save(Menu.construct("./images/xxx", "통치킨버거", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, alldaykingAndChicken, MenuType.BURGER));

        menuRepository.save(Menu.construct("./images/xxx", "쉐이킹프라이", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "바삭킹8조각+소스", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "바삭킹", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "21치즈스틱", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "크라미모짜볼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "코코넛쉬림프+스위트칠리소스", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "너겟킹", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "치즈프라이", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "프렌치프라이", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "시즈닝", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "어니언링", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "코올슬로", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "콘샐러드", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));
        menuRepository.save(Menu.construct("./images/xxx", "사이드소스", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, side, MenuType.SIDE));

        menuRepository.save(Menu.construct("./images/xxx", "제로톡톡", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "아메리카노", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "핫/아이스초코", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "씨그램", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "코카콜라", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "코카콜라 제로", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "스프라이트", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "미닛메이드 오렌지", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));
        menuRepository.save(Menu.construct("./images/xxx", "순수(미네랄워터)", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.BEVERAGE));

        menuRepository.save(Menu.construct("./images/xxx", "선데 초코바나나", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.DESSERT));
        menuRepository.save(Menu.construct("./images/xxx", "선데 딸기바나나", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.DESSERT));
        menuRepository.save(Menu.construct("./images/xxx", "선데 바나나", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.DESSERT));
        menuRepository.save(Menu.construct("./images/xxx", "선데", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.DESSERT));
        menuRepository.save(Menu.construct("./images/xxx", "콘 아이스크림", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.DESSERT));
        menuRepository.save(Menu.construct("./images/xxx", "컵 아이스크림", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, beverageAndDessert, MenuType.DESSERT));
    }
}
