package blowest.kiosk.service;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired private StoreService storeService;
    @Autowired private StoreRepository storeRepository;

    @Test
    @DisplayName("에러 체크")
    public void testError() {
        assertThrows(NoResultException.class,
                () -> storeService.activateStore(1L));
    }

    @Test
    @DisplayName("Store 생성 테스트")
    public void testCreateStore() {
        var storeId = storeService.createStore(StoreRequestDto.construct("가게1"));
        var storeRetrieved = storeRepository.findById(storeId);
        assertThat(storeRetrieved.isPresent()).isEqualTo(true);
        assertThat(storeRetrieved.get().getId()).isEqualTo(storeId);
        assertThat(storeRetrieved.get().getName()).isEqualTo("가게1");
    }

    @Test
    @DisplayName("모든 Store 조회 테스트")
    public void testRetrieveAllStores() {
        assertThrows(NoResultException.class,
                () -> storeService.retrieveAllStores());

        int size = 3;
        List<Long> generatedStoreIds = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            generatedStoreIds.add(storeService.createStore(StoreRequestDto.construct("가게" + i)));
        }

        var storeResponseDtoList = storeService.retrieveAllStores();
        assertThat(storeResponseDtoList.size()).isEqualTo(size);

        for (int i = 0; i < size; i++) {
            assertThat(storeResponseDtoList.get(i).getId()).isEqualTo(generatedStoreIds.get(i));
            assertThat(storeResponseDtoList.get(i).getName()).isEqualTo("가게" + i);
        }
    }
}