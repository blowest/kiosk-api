package blowest.kiosk.controller;

import blowest.kiosk.dto.StoreRequestDto;
import blowest.kiosk.dto.StoreResponseDto;
import blowest.kiosk.repository.StoreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping()
    public Long create(@RequestBody StoreRequestDto request) {
        return storeRepository.save(request.toEntity()).getId();
    }

    @GetMapping()
    public List<StoreResponseDto> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(x -> new StoreResponseDto(x.getId(), x.getName(), x.getCreatedTime(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

}
