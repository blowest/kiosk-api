package blowest.kiosk.controller;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.repository.StoreRepository;
import blowest.kiosk.service.StoreService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class StoreController {

    private final StoreService storeService;

    private final StoreRepository storeRepository;

    private final EntityManager em;

    public StoreController(StoreService storeService, StoreRepository storeRepository, EntityManager em) {
        this.storeService = storeService;
        this.storeRepository = storeRepository;
        this.em = em;
    }

    @GetMapping("/store/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/store")
    public Long create(@RequestBody StoreRequestDto request) {
        return storeService.createStore(request);
    }

    @GetMapping("/store")
    public List<StoreResponseDto> findAll() {
        return storeService.retrieveAllStores();
    }

    @GetMapping("/store/{id}")
    public StoreResponseDto findStore(@PathVariable Long id) {
        return storeService.retrieveStore(id);
    }

    @PatchMapping("/store/{id}")
    public Long updateStore(@PathVariable Long id, @RequestBody StoreRequestDto requestDto) {
        return storeService.updateStore(id, requestDto);
    }

    @DeleteMapping("/store/deactivate/{id}")
    public void deactivateStore(@PathVariable Long id) {
        storeService.deactivateStore(id);
        return;
    }

    @PatchMapping("/store/activate/{id}")
    public void activateStore(@PathVariable Long id) {
        storeService.activateStore(id);

    }
}
