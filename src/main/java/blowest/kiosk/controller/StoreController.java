package blowest.kiosk.controller;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.entity.Store;
import blowest.kiosk.repository.StoreRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class StoreController {

    private final StoreRepository storeRepository;

    private final EntityManager em;

    public StoreController(StoreRepository storeRepository, EntityManager em) {
        this.storeRepository = storeRepository;
        this.em = em;
    }

    @GetMapping("/store/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/store")
    @Transactional
    public Long create(@RequestBody StoreRequestDto request) {
        return storeRepository.save(request.toEntity()).getId();
    }

    @GetMapping("/store")
    @Transactional(readOnly = true)
    public List<StoreResponseDto> findAll() {
        return storeRepository.findAllByActivated(true)
                .stream()
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedTime(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @GetMapping("/store/{id}")
    @Transactional(readOnly = true)
    public StoreResponseDto findStore(@PathVariable Long id) {
        return storeRepository.findByIdAndActivated(id, true)
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedTime(), x.getLastModifiedDate()))
                .orElse(null);
    }

    @PatchMapping("/store/{id}")
    @Transactional
    public Long updateStore(@PathVariable Long id, @RequestBody StoreRequestDto request) {
        var store = storeRepository.findByIdAndActivated(id, true);

        if (!store.isPresent()) {
            return null;
        }

        store.get().setName(request.getName());
        em.flush();
        em.clear();
        return store.get().getId();
    }

    @DeleteMapping("/store/deactivate/{id}")
    @Transactional
    public void deactivateStore(@PathVariable Long id) {
        var store = storeRepository.findByIdAndActivated(id, true);

        if (!store.isPresent()) {
            return;
        }

        store.get().setActivated(false);
        em.flush();
        em.clear();
        return;
    }

    @PatchMapping("/store/activate/{id}")
    @Transactional
    public void activateStore(@PathVariable Long id) {
        var store = storeRepository.findByIdAndActivated(id, false);

        if (!store.isPresent()) {
            return;
        }

        store.get().setActivated(true);
        em.flush();
        em.clear();
        return;
    }
}
