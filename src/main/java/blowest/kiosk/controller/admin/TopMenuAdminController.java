package blowest.kiosk.controller.admin;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.dto.TopMenuResponseDto;
import blowest.kiosk.service.TopMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class TopMenuAdminController {

    private final TopMenuService topMenuService;

    @DeleteMapping("/top_menus/{id}")
    public Long deactivate(@PathVariable Long id) {
        return topMenuService.deactivate(id);
    }

    @PostMapping("/top_menus/{id}/activate")
    public Long activate(@PathVariable Long id) {
        return topMenuService.activate(id);
    }

    @GetMapping("/top_menus/{id}")
    public TopMenuResponseDto retrieve(@PathVariable Long id) {
        return topMenuService.retrieve(id);
    }

    @PatchMapping("/top_menus/{id}")
    public Long update(@PathVariable Long id, @RequestBody TopMenuRequestDto requestDto) {
        return topMenuService.update(id, requestDto);
    }

    @PostMapping("/top_menus")
    public Long create(@RequestBody TopMenuRequestDto requestDto) {
        return topMenuService.create(requestDto);
    }
}
