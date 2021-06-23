package blowest.kiosk.service;

import blowest.kiosk.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
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
class MenuServiceTest {

    @Autowired private MenuService menuService;

    @Test
    @DisplayName("모든 메뉴 조회")
    public void testRetrieveAllMenus() {
        var menuResponseDtos = menuService.retrieveAll();

        assertThat(menuResponseDtos.get(0).getId()).isEqualTo(1L);
        assertThat(menuResponseDtos.size()).isEqualTo(4);
    }

}