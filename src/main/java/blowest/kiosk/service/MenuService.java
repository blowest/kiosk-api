package blowest.kiosk.service;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.repository.MenuRepository;
import blowest.kiosk.repository.MenuTypeRepository;
import blowest.kiosk.repository.TopMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final TopMenuRepository topMenuRepository;

    private final MenuTypeRepository menuTypeRepository;

    private final MenuRepository menuRepository;

    private final EntityManager em;

    public MenuService(TopMenuRepository topMenuRepository, MenuTypeRepository menuTypeRepository, MenuRepository menuRepository, EntityManager em) {
        this.topMenuRepository = topMenuRepository;
        this.menuTypeRepository = menuTypeRepository;
        this.menuRepository = menuRepository;
        this.em = em;
    }

    @Transactional
    public Long create(MenuRequestDto requestDto) {
        var topMenu = topMenuRepository.findByIdAndActivatedTrue(requestDto.getTopMenuId()).orElse(null);
        var menuType = menuTypeRepository.findByIdAndActivatedTrue(requestDto.getMenuTypeId()).orElse(null);

        // Todo
        //      1. toEntity 함수에 넘어가는 requestDto에 FK정보들은 필요없는데 넘어감 -> PathVariable이나 RequestParameter로 해결해야할듯
        return menuRepository.save(requestDto.toEntity(requestDto, topMenu, menuType)).getId();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDto> retrieveAll() {
        return menuRepository.findAllByActivatedTrue()
                .stream()
                .map(x -> new MenuResponseDto(x.getId(), x.getImagePath(), x.isBest(), x.getMinimumCost()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuResponseDto retrieve(Long id) {
        return menuRepository.findByIdAndActivatedTrue(id)
                .map(x -> new MenuResponseDto(x.getId(), x.getImagePath(), x.isBest(), x.getMinimumCost()))
                .orElse(null);
    }

    @Transactional()
    public Long update(Long id, MenuRequestDto requestDto) {
        var topMenu = topMenuRepository.findByIdAndActivatedTrue(requestDto.getTopMenuId()).orElse(null);
        var menuType = menuTypeRepository.findByIdAndActivatedTrue(requestDto.getMenuTypeId()).orElse(null);
        var menu = menuRepository.findByIdAndActivatedTrue(id).orElse(null);

        menu.setImagePath(requestDto.getImagePath());
        menu.setBest(requestDto.isBest());
        menu.setMinimumCost(requestDto.getMinimumCost());
        menu.setTopMenu(topMenu);
        menu.setMenuType(menuType);

        em.flush();
        em.clear();

        return menu.getId();
    }

    @Transactional
    public void deactivate(Long id) {
        var menu = menuRepository.findByIdAndActivatedTrue(id).orElse(null);
        menu.setActivated(false);

        em.flush();
        em.clear();

        return;
    }

    public void activate(Long id) {
        var menu = menuRepository.findByIdAndActivatedFalse(id).orElse(null);
        menu.setActivated(true);

        em.flush();
        em.clear();

        return;
    }
}
