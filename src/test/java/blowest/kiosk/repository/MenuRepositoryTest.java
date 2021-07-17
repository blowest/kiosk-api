package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.entity.status.MenuType;
import blowest.kiosk.entity.status.TierStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MenuRepositoryTest {

    @Autowired
    TopMenuRepository topMenuRepository;
    @Autowired
    MenuRepository menuRepository;

    @Test
    @DisplayName("상단메뉴에 해당하는 활성화된 모든 메뉴 개수 조회")
    public void testCountMenuByTopMenuId() {
        var premium = topMenuRepository.save(TopMenu.construct("프리미엄", ActivationStatus.ACTIVATED, null));

        var menus = new ArrayList<Menu>();
        menus.add(Menu.create("./images/xxx", "몬스터X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "통새우X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "콰트로치즈X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스통베이컨와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스콰트로치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스머쉬룸와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));

        menus.add(Menu.create("./images/xxx", "몬스터와퍼", 8500, TierStatus.NORMAL, ActivationStatus.DEACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "콰트로치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.DEACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "통새우와퍼", 8500, TierStatus.NORMAL, ActivationStatus.DEACTIVATED, premium, MenuType.BURGER));

        for (Menu menu : menus) {
            menuRepository.save(menu);
        }

        var menusActivated = menus.stream()
                .filter(x -> x.getActivationStatus() == ActivationStatus.ACTIVATED)
                .collect(Collectors.toList());

        var quantity = menuRepository.countMenusByTopMenuId(premium.getId());

        assertThat(quantity.isPresent()).isEqualTo(true);
        assertThat(quantity.get()).isEqualTo(menusActivated.size());
    }

    @Test
    @DisplayName("상단 메뉴별 활성화된 메뉴에 대한 페이징 결과 테스트")
    public void testFindMenusByTopMenuId() {
        var premium = topMenuRepository.save(TopMenu.construct("프리미엄", ActivationStatus.ACTIVATED, null));

        var menus = new ArrayList<Menu>();
        menus.add(Menu.create("./images/xxx", "몬스터X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "통새우X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "콰트로치즈X", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스통베이컨와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스콰트로치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스머쉬룸와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "기네스와퍼", 8500, TierStatus.NORMAL, ActivationStatus.ACTIVATED, premium, MenuType.BURGER));

        menus.add(Menu.create("./images/xxx", "몬스터와퍼", 8500, TierStatus.NORMAL, ActivationStatus.DEACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "콰트로치즈와퍼", 8500, TierStatus.NORMAL, ActivationStatus.DEACTIVATED, premium, MenuType.BURGER));
        menus.add(Menu.create("./images/xxx", "통새우와퍼", 8500, TierStatus.NORMAL, ActivationStatus.DEACTIVATED, premium, MenuType.BURGER));

        for (Menu menu : menus) {
            menuRepository.save(menu);
        }

        var menusActivated = menus.stream()
                .filter(x -> x.getActivationStatus() == ActivationStatus.ACTIVATED)
                .collect(Collectors.toList());

        final int  OFFSET = 0;
        final int  PAGE_SIZE = 3;
        var firstPage = menus.stream()
                .sorted(Comparator.comparing(Menu::getId))
                .skip(OFFSET * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .collect(Collectors.toList());
        PageRequest pageRequest = PageRequest.of(OFFSET, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id"));
        var menusPaged = menuRepository.findMenusByTopMenuId(premium.getId(), pageRequest);

        assertThat(menusPaged.getTotalElements()).isEqualTo(menusActivated.size());

        for (int i = 0; i < PAGE_SIZE; i++) {
            assertThat(menusPaged.getContent().get(i)).isSameAs(firstPage.get(i));
        }
    }
}