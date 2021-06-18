package blowest.kiosk.controller;

import blowest.kiosk.dto.MenuDetailRequestDto;
import blowest.kiosk.dto.MenuDetailResponseDto;
import blowest.kiosk.service.MenuDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuDetailController {

    private final MenuDetailService menuDetailService;

    public MenuDetailController(MenuDetailService menuDetailService){
        this.menuDetailService = menuDetailService;
    }

    @PostMapping("/menu_details")
    public Long create(@RequestBody MenuDetailRequestDto requestDto){
        return menuDetailService.create(requestDto);
    }

    @GetMapping("/menu_details")
    public List<MenuDetailResponseDto> retrieveAll(){
        return menuDetailService.retrieveAll();
    }

    @GetMapping("/menu_details/{id}")
    public MenuDetailResponseDto retrieve(@PathVariable Long id){
        return menuDetailService.retrieve(id);
    }

    @PatchMapping("/menu_details/{id}")
    public Long update(@PathVariable Long id, @RequestBody MenuDetailRequestDto requestDto) {
        return menuDetailService.update(id,requestDto);
    }

    @DeleteMapping("/menu_details/{id}")
    public Long deactivate(@PathVariable Long id) {
        return menuDetailService.deactivate(id);
    }

    @PatchMapping("/menu_details/{id}/activate")
    public Long activate(@PathVariable Long id){
        return menuDetailService.activate(id);
    }

}
