package blowest.kiosk.controller;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

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

    @PatchMapping("/menus/{id}/activate")
    public void activateStore(@PathVariable Long id) {
        menuService.activate(id);
        return;
    }


}
