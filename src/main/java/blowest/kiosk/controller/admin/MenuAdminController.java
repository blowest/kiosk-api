package blowest.kiosk.controller.admin;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.repository.MenuDslRepository;
import blowest.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MenuAdminController {

    private final MenuService menuService;

    private final MenuDslRepository menuDslRepository;

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

    @GetMapping("/v2/menus")
    public List<MenuResponseDto> retrieveAllMenus() {
        var allMenu = menuDslRepository.findAllMenu();
        return allMenu.stream().map(x -> MenuResponseDto.create(x.getId(), x.getImagePath(), x.getName(),
                x.getCost(), x.getTierStatus())).collect(Collectors.toList());
    }
}
