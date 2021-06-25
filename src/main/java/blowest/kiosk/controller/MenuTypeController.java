package blowest.kiosk.controller;

import blowest.kiosk.dto.MenuTypeRequestDto;
import blowest.kiosk.dto.MenuTypeResponseDto;
import blowest.kiosk.repository.MenuTypeRepository;
import blowest.kiosk.service.MenuTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MenuTypeController {

    private final MenuTypeService menuTypeService;

    @GetMapping("/menu_types/test")
    public String test(){return "Test";}

    @PostMapping("/menu_types")
    public Long create(@RequestBody MenuTypeRequestDto request){
        return menuTypeService.createMenuType(request);
    }

    @GetMapping("/menu_types")
    public List<MenuTypeResponseDto> findAll(){
        return menuTypeService.retrieveAllMenuTypes();
    }

    @GetMapping("/menu_types/{id}")
    public MenuTypeResponseDto findMenuType(@PathVariable Long id){
        return menuTypeService.retrieveMenuType(id);
    }

    @PatchMapping("/menu_types/{id}")
    public Long updateMenuType(@PathVariable Long id, @RequestBody MenuTypeRequestDto requestDto){
        return menuTypeService.updateMenuType(id, requestDto);
    }

    @DeleteMapping("/menu_types/{id}")
    public void deactivateMenuType(@PathVariable Long id){
        menuTypeService.deactivateMenuType(id);
    }

    @PostMapping("/menu_types/{id}/activate")
    public void activateMenuType(@PathVariable Long id){
        menuTypeService.activateMenuType(id);
    }
}
