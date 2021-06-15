package blowest.kiosk.controller;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

    @PostMapping("/menu")
    public Long create(@RequestBody MenuRequestDto requestDto) {
        return 0L;
    }

    @GetMapping("/menus")
    public List<MenuResponseDto> retrieveAll() {
        return null;
    }
}
