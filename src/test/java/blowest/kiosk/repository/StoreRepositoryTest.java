package blowest.kiosk.repository;

import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreRepositoryTest {

    @Autowired
    TopMenuRepository topMenuRepository;

    @Test
    @DisplayName("모든 상단메뉴 조회 테스트")
    public void testGetAllTopMenus() {
        var menusActivated = new ArrayList<TopMenu>();
        menusActivated.add(TopMenu.construct("스페셜&할인팩", ActivationStatus.ACTIVATED, null));
        menusActivated.add(TopMenu.construct("프리미엄", ActivationStatus.ACTIVATED, null));
        menusActivated.add(TopMenu.construct("와퍼", ActivationStatus.ACTIVATED, null));
        menusActivated.add(TopMenu.construct("주니어&버거", ActivationStatus.ACTIVATED, null));
        menusActivated.add(TopMenu.construct("올데이킹&치킨", ActivationStatus.ACTIVATED, null));
        menusActivated.add(TopMenu.construct("사이드", ActivationStatus.ACTIVATED, null));
        for (TopMenu topMenu : menusActivated) {
            topMenuRepository.save(topMenu);
        }

        var menusDeactivated = new ArrayList<TopMenu>();
        menusDeactivated.add(TopMenu.construct("음료&디저트", ActivationStatus.DEACTIVATED, null));
        for (TopMenu topMenu : menusDeactivated) {
            topMenuRepository.save(topMenu);
        }

        var allActivated = topMenuRepository.findAllActivated();

        assertThat(allActivated.size()).isEqualTo(menusActivated.size());
    }

}