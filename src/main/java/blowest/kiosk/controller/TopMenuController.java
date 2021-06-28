package blowest.kiosk.controller;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.dto.TopMenuResponseDto;
import blowest.kiosk.service.TopMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TopMenuController {

    private final TopMenuService topMenuService;

    @PostMapping("/top_menus")
    public Long create(@RequestBody TopMenuRequestDto requestDto) {
        return topMenuService.create(requestDto);
    }

    @GetMapping("/top_menus")
    public List<TopMenuResponseDto> retrieveAll() {
        return topMenuService.retrieveAll();
    }

    @GetMapping("/top_menus/{id}")
    public TopMenuResponseDto retrieve(@PathVariable Long id) {
        return topMenuService.retrieve(id);
    }

    @PatchMapping("/top_menus/{id}")
    public Long update(@PathVariable Long id, @RequestBody TopMenuRequestDto requestDto) {
        return topMenuService.update(id, requestDto);
    }

    @DeleteMapping("/top_menus/{id}")
    public Long deactivate(@PathVariable Long id) {
        return topMenuService.deactivate(id);
    }

    @PostMapping("/top_menus/{id}/activate")
    public Long activate(@PathVariable Long id) {
        return topMenuService.activate(id);
    }
}
