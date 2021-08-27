package blowest.kiosk.service;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long createStore(StoreRequestDto request) {
        return storeRepository.save(request.toEntity()).getId();
    }

    public List<StoreResponseDto> retrieveAllStores() {
        var stores = storeRepository.findAllActivated();

        if (stores.isEmpty()) {
            throw new NoResultException("등록된 매장정보가 없습니다.");
        }
        return stores.stream()
                .map(x -> StoreResponseDto.construct(x.getId(), x.getName(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    public StoreResponseDto retrieveStore(Long id) {
        var store = storeRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당 매장 정보가 없습니다."));

        return StoreResponseDto.construct(store.getId(), store.getName(), store.getCreatedDate(), store.getLastModifiedDate());
    }

    @Transactional
    public Long updateStore(Long id, StoreRequestDto requestDto) {
        var store = storeRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당 매장 정보가 없습니다."));

        store.update(requestDto.getName());

        return store.getId();
    }

    @Transactional
    public void deactivateStore(Long id) {
        var store = storeRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당 매장 정보가 없습니다."));

        store.deactivate();

        return;
    }

    @Transactional
    public void activateStore(Long id) {
        var store = storeRepository.findOneDeactivated(id)
                .orElseThrow(() -> new NoResultException("해당 매장 정보가 없습니다."));

        store.activate();

        return;
    }
}
