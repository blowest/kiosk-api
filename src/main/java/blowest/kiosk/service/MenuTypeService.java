package blowest.kiosk.service;


import blowest.kiosk.dto.MenuTypeRequestDto;
import blowest.kiosk.dto.MenuTypeResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.MenuTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuTypeService {

    private final MenuTypeRepository menuTypeRepository;

    @Transactional
    public Long createMenuType(MenuTypeRequestDto request){
        return menuTypeRepository.save(request.toEntity()).getId();
    }

    public List<MenuTypeResponseDto> retrieveAllMenuTypes(){
        var menuTypes = menuTypeRepository.findAllActivated();
        if (menuTypes.isEmpty()){
            throw new NoResultException("등록된 메뉴타입정보가 없습니다.");
        }
        return menuTypes
                .stream()
                .map(x-> MenuTypeResponseDto.construct(x.getId(),x.getName(),x.getCreatedDate(),x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    public MenuTypeResponseDto retrieveMenuType(Long id){
        var menuType = menuTypeRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴타입 정보가 없습니다."));
        return MenuTypeResponseDto.construct(menuType.getId(),menuType.getName(),menuType.getCreatedDate(),menuType.getLastModifiedDate());
    }

    @Transactional
    public Long updateMenuType(Long id, MenuTypeRequestDto requestDto){
        var menuType = menuTypeRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴타입 정보가 없습니다."));

        menuType.update(requestDto.getName());

        return menuType.getId();
    }

    @Transactional
    public void deactivateMenuType(Long id){
        var menuType = menuTypeRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴타입 정보가 없습니다."));

        menuType.deactivate();

        return;
    }

    @Transactional
    public void activateMenuType(Long id){
        var menuType = menuTypeRepository.findOneDeactivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 메뉴타입 정보가 없습니다."));

        menuType.activate();

        return;
    }
}
