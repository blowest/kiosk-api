package blowest.kiosk.service;

import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.entity.Menu;
import blowest.kiosk.repository.MenuRepository;
import blowest.kiosk.repository.TopMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final TopMenuRepository topMenuRepository;

    private final MenuRepository menuRepository;

    @Transactional
    public Long create(MenuRequestDto requestDto) {
        var topMenu = topMenuRepository.findOneActivated(requestDto.getTopMenuId())
                .orElseThrow(() -> new NoResultException("등록된 상위메뉴 정보가 없습니다."));

        return menuRepository.save(requestDto.toEntity(topMenu)).getId();
    }

    public List<MenuResponseDto> retrieveMenusByTopMenuId(Long topMenuId) {
        List<Menu> menus = menuRepository.findAllActivatedByTopMenuId(topMenuId);

        return menus.stream().map(x -> MenuResponseDto.construct(x.getId(), x.getImagePath(), x.getName(),
                x.getCost(), x.getTierStatus())).collect(Collectors.toList());
    }

    public List<MenuResponseDto> retrieveAll() {
        var menus = menuRepository.findAllActivated();
        if (menus.isEmpty()){
            throw new NoResultException("등록된 메뉴 정보가 없습니다.");
        }
        return menus
                .stream()
                .map(x -> MenuResponseDto.construct(x.getId(), x.getImagePath(), x.getName(), x.getCost(), x.getTierStatus()))
                .collect(Collectors.toList());
    }

    public MenuResponseDto retrieve(Long id) {
        var menu = menuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴정보가 없습니다."));
        return MenuResponseDto.construct(menu.getId(), menu.getImagePath(), menu.getName(), menu.getCost(), menu.getTierStatus());
    }

    @Transactional
    public Long update(Long id, MenuRequestDto requestDto) {
        var topMenu = topMenuRepository.findById(requestDto.getTopMenuId())
                .orElseThrow(() -> new NoResultException("해당하는 상위메뉴가 없습니다."));
        var menu = menuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴가 없습니다."));

        menu.update(requestDto.getImagePath(), requestDto.getName(), requestDto.getCost(), requestDto.getTierStatus(), topMenu, requestDto.getMenuType());

        return menu.getId();
    }

    @Transactional
    public void deactivate(Long id) {
        var menu = menuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴가 없습니다."));

        menu.deactivate();

        return;
    }

    @Transactional
    public void activate(Long id) {
        var menu = menuRepository.findOneDeactivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴가 없습니다."));

        menu.activate();

        return;
    }
}
