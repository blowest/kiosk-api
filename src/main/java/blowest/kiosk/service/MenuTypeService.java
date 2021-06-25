package blowest.kiosk.service;


import blowest.kiosk.dto.MenuTypeRequestDto;
import blowest.kiosk.dto.MenuTypeResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.MenuTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuTypeService {

    private final MenuTypeRepository menuTypeRepository;

    private final EntityManager em;

    public MenuTypeService(MenuTypeRepository menuTypeRepository, EntityManager em){
        this.menuTypeRepository = menuTypeRepository;
        this.em = em;
    }

    @Transactional
    public Long createMenuType(MenuTypeRequestDto request){
        return menuTypeRepository.save(request.toEntity()).getId();
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public MenuTypeResponseDto retrieveMenuType(Long id){
        var menuTypes = menuTypeRepository.findOneActivated(id);
        if (!menuTypes.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }
        return menuTypes
                .map(x-> MenuTypeResponseDto.construct(x.getId(),x.getName(),x.getCreatedDate(),x.getLastModifiedDate()))
                .orElse(null);
    }

    @Transactional
    public Long updateMenuType(Long id, MenuTypeRequestDto requestDto){
        var menuType = menuTypeRepository.findOneActivated(id);

        if (!menuType.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }
        menuType.get().update(requestDto.getName());

        return menuType.get().getId();
    }

    @Transactional
    public void deactivateMenuType(Long id){
        var menuType = menuTypeRepository.findOneActivated(id);

        if(!menuType.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }
        menuType.get().updateActivation(ActivationStatus.DEACTIVATED);

        return;
    }

    @Transactional
    public void activateMenuType(Long id){
        var menuType = menuTypeRepository.findOneDeactivated(id);

        if (!menuType.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }
        menuType.get().updateActivation(ActivationStatus.ACTIVATED);

        return;
    }
}
