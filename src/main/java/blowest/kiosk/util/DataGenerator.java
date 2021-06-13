package blowest.kiosk.util;

import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.TopMenu;
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

    public DataGenerator(StoreRepository storeRepository, TopMenuRepository topMenuRepository, MenuTypeRepository menuTypeRepository) {
        this.storeRepository = storeRepository;
        this.topMenuRepository = topMenuRepository;
        this.menuTypeRepository = menuTypeRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        var burgerking = storeRepository.save(new Store("버거킹", true));
        storeRepository.save(new Store("맥도날드", true));
        storeRepository.save(new Store("롯데리아", true));
        storeRepository.save(new Store("맘스터치", true));

        topMenuRepository.save(new TopMenu("스페셜&할인팩", true, burgerking));
        topMenuRepository.save(new TopMenu("프리미엄", true, burgerking));
        topMenuRepository.save(new TopMenu("와퍼", true, burgerking));
        topMenuRepository.save(new TopMenu("주니어&버거", true, burgerking));
        topMenuRepository.save(new TopMenu("올데이킹&치킨버거", true, burgerking));
        topMenuRepository.save(new TopMenu("사이드", true, burgerking));
        topMenuRepository.save(new TopMenu("음료&디저트", true, burgerking));

        menuTypeRepository.save(new MenuType("버거",true));
        menuTypeRepository.save(new MenuType("사이드",true));
        menuTypeRepository.save(new MenuType("음료",true));
    }
}
