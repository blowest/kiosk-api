package blowest.kiosk.service;

import blowest.kiosk.dto.MenuPagedResponseDto;
import blowest.kiosk.dto.MenuRequestDto;
import blowest.kiosk.dto.MenuResponseDto;
import blowest.kiosk.entity.Menu;
import blowest.kiosk.repository.CustomMenuRepository;
import blowest.kiosk.repository.MenuRepository;
import blowest.kiosk.repository.TopMenuRepository;
import blowest.kiosk.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private final CustomMenuRepository customMenuRepository;

    @Transactional
    public Long create(MenuRequestDto requestDto) {
        var topMenu = topMenuRepository.findOneActivated(requestDto.getTopMenuId())
                .orElseThrow(() -> new NoResultException("등록된 상위메뉴 정보가 없습니다."));

        return menuRepository.save(requestDto.toEntity(topMenu)).getId();
    }

    @Transactional
    public List<Long> createMenus(List<MenuRequestDto> request) {
        var menus = request.stream().map(x ->
                x.toEntity(
                        topMenuRepository.findOneActivated(x.getTopMenuId()).orElseThrow(
                                () -> new NoResultException("등록된 상위메뉴 정보가 없습니다.")
                        )
                )).collect(Collectors.toList());
        return menuRepository.saveAll(menus).stream().map(x -> x.getId()).collect(Collectors.toList());
    }

    public Long countMenusRetrievedByTopMenuId(Long topMenuId) {
        var byTopMenuId = menuRepository.countMenusByTopMenuId(topMenuId);
        if (byTopMenuId.isEmpty()) throw new NoResultException("상위메뉴 아이디에 해당하는 메뉴가 없습니다.");

        return byTopMenuId.get();
    }

    public MenuPagedResponseDto retrieveMenusByTopMenuId(Long topMenuId, int offset, int size) {
        PageRequest pageRequest = PageRequest.of(offset, size, Sort.by(Sort.Direction.ASC, "id"));
        var result = menuRepository.findMenusByTopMenuId(topMenuId, pageRequest);

        var totalPages = result.getTotalPages();
        var totalMenus = result.getTotalElements();
        var menus = result.getContent();
        var menuResponseDtoList = menus.stream().map(x -> MenuResponseDto.create(x.getId(), x.getImagePath(), x.getName(),
                x.getCost(), x.getTierStatus())).collect(Collectors.toList());

        return MenuPagedResponseDto.create(totalPages, totalMenus, menuResponseDtoList);
    }

    public List<MenuResponseDto> retrieveAll() {
        var menus = menuRepository.findAllActivated();
        if (menus.isEmpty()){
            throw new NoResultException("등록된 메뉴 정보가 없습니다.");
        }
        return menus
                .stream()
                .map(x -> MenuResponseDto.create(x.getId(), x.getImagePath(), x.getName(), x.getCost(), x.getTierStatus()))
                .collect(Collectors.toList());
    }

    public MenuResponseDto retrieve(Long id) {
        var menu = menuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴정보가 없습니다."));
        return MenuResponseDto.create(menu.getId(), menu.getImagePath(), menu.getName(), menu.getCost(), menu.getTierStatus());
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

    public MenuPagedResponseDto retrieveMenusByTopMenuIdV2(Long topMenuId, int offset, int size) {
        var result = customMenuRepository.findMenusWithPagination(topMenuId, offset, size);

        var totalPages = Pagination.getTotalPages(result.getTotal(), size);
        var totalMenus = result.getTotal();
        var menus = result.getResults();

        var menuResponseDtoList = menus.stream().map(x -> MenuResponseDto.create(x.getId(), x.getImagePath(), x.getName(),
                x.getCost(), x.getTierStatus())).collect(Collectors.toList());

        return MenuPagedResponseDto.create(totalPages, totalMenus, menuResponseDtoList);
    }
}
