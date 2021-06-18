package blowest.kiosk.controller;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.repository.StoreRepository;
import blowest.kiosk.service.StoreService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

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

    @GetMapping("/stores/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/stores")
    public Long create(@RequestBody StoreRequestDto request) {
        return storeService.createStore(request);
    }

    @GetMapping("/stores")
    public List<StoreResponseDto> findAll() {
        return storeService.retrieveAllStores();
    }

    @GetMapping("/stores/{id}")
    public StoreResponseDto findStore(@PathVariable Long id) {
        return storeService.retrieveStore(id);
    }

    @PatchMapping("/stores/{id}")
    public Long updateStore(@PathVariable Long id, @RequestBody StoreRequestDto requestDto) {
        return storeService.updateStore(id, requestDto);
    }

    @DeleteMapping("/stores/{id}/deactivate")
    public void deactivateStore(@PathVariable Long id) {
        storeService.deactivateStore(id);
        return;
    }

    @PatchMapping("/stores/{id}/activate")
    public void activateStore(@PathVariable Long id) {
        storeService.activateStore(id);
    }
}
