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
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/v1/top_menus/{id}/menus/count")
    public Long countMenusRetrievedByTopMenuId(@PathVariable(name = "id") Long topMenuId) {
        return menuService.countMenusRetrievedByTopMenuId(topMenuId);
    }

    @GetMapping("/v1/top_menus/{id}/menus")
    public MenuPagedResponseDto retrieveByTopMenuId(@PathVariable(name = "id") Long topMenuId, @RequestParam int offset, @RequestParam int size) {
        return menuService.retrieveMenusByTopMenuId(topMenuId, offset, size);
    }

    @GetMapping("/v2/top_menus/{id}/menus")
    public MenuPagedResponseDto retrieveByTopMenuIdV2(@PathVariable(name = "id") Long topMenuId, @RequestParam int offset, @RequestParam int size) {
        return menuService.retrieveMenusByTopMenuIdV2(topMenuId, offset, size);
    }
}
