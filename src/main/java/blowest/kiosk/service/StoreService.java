package blowest.kiosk.service;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
        return storeRepository.findAllByActivatedTrue()
                .stream()
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreResponseDto retrieveStore(Long id) {
        return storeRepository.findByIdAndActivatedTrue(id)
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedDate(), x.getLastModifiedDate()))
                .orElse(null);
    }

    @Transactional
    public Long updateStore(Long id, StoreRequestDto requestDto) {
        var store = storeRepository.findByIdAndActivatedTrue(id);

        if (!store.isPresent()) {
            return null;
        }

        var storeRetrieved = store.get();
        storeRetrieved.setName(requestDto.getName());
        em.flush();
        em.clear();

        return storeRetrieved.getId();
    }

    @Transactional
    public void deactivateStore(Long id) {
        var store = storeRepository.findByIdAndActivatedTrue(id);

        if (!store.isPresent()) {
            return;
        }
        store.get().setActivated(false);
        em.flush();
        em.clear();
        return;
    }

    @Transactional
    public void activateStore(Long id) {
        var store = storeRepository.findByIdAndActivatedFalse(id);

        if (!store.isPresent()) {
            return;
        }

        store.get().setActivated(true);
        em.flush();
        em.clear();
        return;
    }
}
