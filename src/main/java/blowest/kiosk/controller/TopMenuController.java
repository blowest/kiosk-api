package blowest.kiosk.controller;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.dto.TopMenuResponseDto;
import blowest.kiosk.service.TopMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TopMenuController {

    private final TopMenuService topMenuService;

    @GetMapping("/v1/top_menus")
    public List<TopMenuResponseDto> retrieveAll() {
        return topMenuService.retrieveAll();
    }

}
