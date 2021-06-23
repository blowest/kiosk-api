package blowest.kiosk.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired private StoreService storeService;

    @Test
    @DisplayName("에러 체크")
    public void testError() {
        assertThrows(NoResultException.class,
                () -> storeService.activateStore(1L));
    }
}