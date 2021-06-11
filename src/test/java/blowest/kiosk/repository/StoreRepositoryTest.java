package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StoreRepositoryTest {

    @Autowired StoreRepository storeRepository;

    @Autowired EntityManager em;

    @Test
    public void testFindStoreActivated() {
        var allByActivated = storeRepository.findAllByActivated(true);
        assertThat(allByActivated.size()).isEqualTo(4);
    }
}