package blowest.kiosk.controller;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.repository.StoreRepository;
import blowest.kiosk.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

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

    @DeleteMapping("/stores/{id}")
    public void deactivateStore(@PathVariable Long id) {
        storeService.deactivateStore(id);
        return;
    }

    @PostMapping("/stores/{id}/activate")
    public void activateStore(@PathVariable Long id) {
        storeService.activateStore(id);
    }
}
