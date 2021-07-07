package blowest.kiosk.repository;

import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.status.ActivationStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class StoreRepositoryTest {

//    @Autowired StoreRepository storeRepository;
//
//    @Autowired EntityManager em;
//
//    @Test
//    @DisplayName("가게 조회 및 정렬 테스트")
//    public void testFindAllStoreActivated() {
//        var burgerking = storeRepository.save(Store.construct("버거킹", ActivationStatus.ACTIVATED));
//        var mcdonald = storeRepository.save(Store.construct("맥도날드", ActivationStatus.ACTIVATED));
//        var lottelia = storeRepository.save(Store.construct("롯데리아", ActivationStatus.ACTIVATED));
//        var momstouch = storeRepository.save(Store.construct("맘스터치", ActivationStatus.ACTIVATED));
//
//        var allByActivated = storeRepository.findAllActivated();
//        assertThat(allByActivated.size()).isEqualTo(4);
//        assertThat(allByActivated.get(0).getId()).isEqualTo(burgerking.getId());
//        assertThat(allByActivated.get(3).getId()).isEqualTo(momstouch.getId());
//    }
//
//    @Test
//    @DisplayName("id로 활성화된 가게 조회")
//    public void testFindOneActivated() {
//        storeRepository.save(Store.construct("버거킹", ActivationStatus.ACTIVATED));
//        storeRepository.save(Store.construct("맥도날드", ActivationStatus.ACTIVATED));
//        storeRepository.save(Store.construct("롯데리아", ActivationStatus.ACTIVATED));
//        storeRepository.save(Store.construct("맘스터치", ActivationStatus.ACTIVATED));
//
//        var stores = storeRepository.findAllActivated();
//        for (int i = 1; i <= stores.size() + 1; i++) {
//            var store = storeRepository.findOneActivated(Long.valueOf(i));
//            if (store.isPresent()) {
//                assertThat(store.get()).isEqualTo(stores.get(i - 1));
//                continue;
//            }
//            assertThrows(NoSuchElementException.class,
//                    () -> store.get());
//        }
//    }
}