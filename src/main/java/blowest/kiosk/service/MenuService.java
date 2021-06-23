package blowest.kiosk.service;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.MenuRepository;
import blowest.kiosk.repository.MenuTypeRepository;
import blowest.kiosk.repository.TopMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
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
        var menus = menuRepository.findAll();
        if (menus.isEmpty()){
            throw new NoResultException("등록된 메뉴 정보가 없습니다.");
        }
        return menus
                .stream()
                .map(x -> new MenuResponseDto(x.getId(), x.getImagePath(), x.isBest(), x.getMinimumCost()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuResponseDto retrieve(Long id) {
        var menus = menuRepository.findById(id);
        if (!menus.isPresent()){
            throw new NoResultException("해당하는 메뉴정보가 없습니다.");
        }
        return menus
                .map(x -> new MenuResponseDto(x.getId(), x.getImagePath(), x.isBest(), x.getMinimumCost()))
                .orElse(null);
    }

    @Transactional()
    public Long update(Long id, MenuRequestDto requestDto) {
        var topMenu = topMenuRepository.findById(requestDto.getTopMenuId());
        if (!topMenu.isPresent()){
            throw new NoResultException("해당하는 상위메뉴가 없습니다.");
        }
        var menuType = menuTypeRepository.findById(requestDto.getMenuTypeId());
        if (!menuType.isPresent()){
            throw new NoResultException("메뉴 타입이 없습니다.");
        }
        var menu = menuRepository.findById(id);
        if (!menu.isPresent()){
            throw new NoResultException("해당하는 메뉴가 없습니다.");
        }

        menu.get().setImagePath(requestDto.getImagePath());
        menu.get().setBest(requestDto.isBest());
        menu.get().setMinimumCost(requestDto.getMinimumCost());
        menu.get().setTopMenu(topMenu.get());
        menu.get().setMenuType(menuType.get());

        return menu.get().getId();
    }

    @Transactional
    public void deactivate(Long id) {
        var menu = menuRepository.findById(id);
        if (!menu.isPresent()){
            throw new NoResultException("해당하는 메뉴가 없습니다.");
        }
        menu.get().updateActivation(ActivationStatus.DEACTIVATED);

        return;
    }

    @Transactional
    public void activate(Long id) {
        var menu = menuRepository.findById(id);
        if (!menu.isPresent()){
            throw new NoResultException("해당하는 메뉴가 없습니다.");
        }
        menu.get().updateActivation(ActivationStatus.ACTIVATED);

        return;
    }
}
