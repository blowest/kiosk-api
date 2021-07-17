package blowest.kiosk.controller;

import blowest.kiosk.dto.MenuPagedResponseDto;
import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/top_menus/{id}/menus/count")
    public Long countMenusRetrievedByTopMenuId(@PathVariable(name = "id") Long topMenuId) {
        return menuService.countMenusRetrievedByTopMenuId(topMenuId);
    }

    @GetMapping("/top_menus/{id}/menus")
    public MenuPagedResponseDto retrieveByTopMenuId(@PathVariable(name = "id") Long topMenuId, @RequestParam int offset, @RequestParam int size) {
        return menuService.retrieveMenusByTopMenuId(topMenuId, offset, size);
    }


}
