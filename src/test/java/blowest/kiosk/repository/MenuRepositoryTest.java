package blowest.kiosk.repository;

import blowest.kiosk.entity.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MenuRepositoryTest {

    @Autowired private MenuRepository menuRepository;

    @Test
    @DisplayName("활성화된 모든 메뉴 검색")
    public void testRetrieveAllMenus() {
//        // Given
//        var menu = Menu.construct()
//        menuService.save(menu);
//
//
//        // When
//        var menus = menuRepository.findAllActivated();
//
//        // Then
//        assertThat(menus.size()).isEqualTo(3);
    }
}