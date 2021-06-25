package blowest.kiosk.service;

import blowest.kiosk.dto.MenuDetailRequestDto;
import blowest.kiosk.dto.MenuDetailResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.MenuDetailRepository;
import blowest.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuDetailService {

    private final MenuRepository menuRepository;
    private final MenuDetailRepository menuDetailRepository;

    @Transactional
    public Long create(MenuDetailRequestDto requestDto){
        var menu = menuRepository.findById(requestDto.getMenuId())
                .orElseThrow(() -> new NoResultException("해당하는 메뉴 정보가 없습니다."));

        return menuDetailRepository.save(requestDto.toEntity(menu)).getId();
    }

    public List<MenuDetailResponseDto> retrieveAll(){
        var menuDetails = menuDetailRepository.findAllActivated();
        if (menuDetails.isEmpty()){
            throw new NoResultException("등록된 상세메뉴 정보가 없습니다.");
        }
        return menuDetails
                .stream()
                .map(x -> MenuDetailResponseDto.construct(x.getId(), x.getName(), x.getCost(), x.getImagePath(),
                        x.getMenu().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    public MenuDetailResponseDto retrieve(Long id){
        var menuDetail = menuDetailRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 상세메뉴 정보가 없습니다."));
        return MenuDetailResponseDto.construct(menuDetail.getId(), menuDetail.getName(), menuDetail.getCost(),
                menuDetail.getImagePath(), menuDetail.getMenu().getId(), menuDetail.getCreatedDate(), menuDetail.getLastModifiedDate());
    }

    @Transactional
    public Long update(Long id, MenuDetailRequestDto requestDto){
        var menu = menuRepository.findById(requestDto.getMenuId())
                .orElseThrow(() -> new NoResultException("해당하는 메뉴 정보가 없습니다."));
        var menuDetail = menuDetailRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 상세메뉴 정보가 없습니다."));

        menuDetail.update(requestDto.getName());
        menuDetail.setMenu(menu);

        return menuDetail.getId();
    }

    @Transactional
    public Long deactivate(Long id){
        var menuDetail = menuDetailRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 상세메뉴 정보가 없습니다."));

        menuDetail.deactivate();

        return menuDetail.getId();
    }

    @Transactional
    public Long activate(Long id){
        var menuDetail = menuDetailRepository.findOneDeactivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 상세메뉴 정보가 없습니다."));

        menuDetail.activate();

        return menuDetail.getId();
    }































}
