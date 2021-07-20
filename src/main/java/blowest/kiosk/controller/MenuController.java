package blowest.kiosk.controller;

import blowest.kiosk.dto.MenuPagedResponseDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.repository.MenuDslRepository;
import blowest.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    private final MenuDslRepository menuDslRepository;

    @GetMapping("/top_menus/{id}/menus/count")
    public Long countMenusRetrievedByTopMenuId(@PathVariable(name = "id") Long topMenuId) {
        return menuService.countMenusRetrievedByTopMenuId(topMenuId);
    }

    @GetMapping("/top_menus/{id}/menus")
    public MenuPagedResponseDto retrieveByTopMenuId(@PathVariable(name = "id") Long topMenuId, @RequestParam int offset, @RequestParam int size) {
        return menuService.retrieveMenusByTopMenuId(topMenuId, offset, size);
    }

    @GetMapping("/menus")
    public List<MenuResponseDto> retrieveAllMenus() {
        var allMenu = menuDslRepository.findAllMenu();
        return allMenu.stream().map(x -> MenuResponseDto.create(x.getId(), x.getImagePath(), x.getName(),
                x.getCost(), x.getTierStatus())).collect(Collectors.toList());
    }


}
