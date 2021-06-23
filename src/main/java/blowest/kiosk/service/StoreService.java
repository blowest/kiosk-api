package blowest.kiosk.service;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    private final EntityManager em;

    public StoreService(StoreRepository storeRepository, EntityManager em) {
        this.storeRepository = storeRepository;
        this.em = em;
    }

    @Transactional
    public Long createStore(StoreRequestDto request) {
        return storeRepository.save(request.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDto> retrieveAllStores() {
        var stores = storeRepository.findAllByActivatedTrue();

        if (stores.isEmpty()) {
            throw new NoResultException("등록된 매장정보가 없습니다.");
        }
        return stores.stream()
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreResponseDto retrieveStore(Long id) {
        var stores = storeRepository.findByIdAndActivatedTrue(id);

        if (!stores.isPresent()){
            throw new NoResultException("해당 매장 정보가 없습니다.");
        }
        return stores
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedDate(), x.getLastModifiedDate()))
                .orElse(null);
    }

    @Transactional
    public Long updateStore(Long id, StoreRequestDto requestDto) {
        var store = storeRepository.findByIdAndActivatedTrue(id);

        if (!store.isPresent()) {
            throw new NoResultException("해당 매장 정보가 없습니다.");
        }

        var storeRetrieved = store.get();
        storeRetrieved.update(requestDto.getName());

        return storeRetrieved.getId();
    }

    @Transactional
    public void deactivateStore(Long id) {
        var store = storeRepository.findByIdAndActivatedTrue(id);

        if (!store.isPresent()) {
            throw new NoResultException("해당 매장 정보가 없습니다.");
        }
        store.get().updateActivation(ActivationStatus.DEACTIVATED);

        return;
    }

    @Transactional
    public void activateStore(Long id) {
        var store = storeRepository.findByIdAndActivatedFalse(id);

        if (!store.isPresent()) {
            throw new NoResultException("해당 매장 정보가 없습니다.");
        }

        store.get().updateActivation(ActivationStatus.ACTIVATED);

        return;
    }
}
