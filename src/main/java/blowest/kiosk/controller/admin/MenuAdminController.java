package blowest.kiosk.controller.admin;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MenuAdminController {

    private final MenuService menuService;

    @PostMapping("/menus")
    public Long create(@RequestBody MenuRequestDto requestDto) {
        return menuService.create(requestDto);
    }

    @GetMapping("/menus")
    public List<MenuResponseDto> retrieveAll() {
        return menuService.retrieveAll();
    }

    @GetMapping("/menus/{id}")
    public MenuResponseDto retrieve(@PathVariable Long id) {
        return menuService.retrieve(id);
    }

    @PatchMapping("/menus/{id}")
    public Long update(@PathVariable Long id, @RequestBody MenuRequestDto requestDto) {
        return menuService.update(id, requestDto);
    }

    @DeleteMapping("/menus/{id}")
    public void deactivateStore(@PathVariable Long id) {
        menuService.deactivate(id);
        return;
    }

    @PostMapping("/menus/{id}/activate")
    public void activateStore(@PathVariable Long id) {
        menuService.activate(id);
        return;
    }
}
