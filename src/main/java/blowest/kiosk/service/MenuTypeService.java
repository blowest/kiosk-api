package blowest.kiosk.service;


import blowest.kiosk.dto.MenuTypeRequestDto;
import blowest.kiosk.dto.MenuTypeResponseDto;
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
        var menuTypes = menuTypeRepository.findAllByActivatedTrue();
        if (menuTypes.isEmpty()){
            throw new NoResultException("등록된 메뉴타입정보가 없습니다.");
        }
        return menuTypes
                .stream()
                .map(x->new MenuTypeResponseDto(x.getId(),x.getName(),x.getCreatedDate(),x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuTypeResponseDto retrieveMenuType(Long id){
        var menuTypes = menuTypeRepository.findByIdAndActivatedTrue(id);
        if (!menuTypes.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }
        return menuTypes
                .map(x-> new MenuTypeResponseDto(x.getId(),x.getName(),x.getCreatedDate(),x.getLastModifiedDate()))
                .orElse(null);
    }

    @Transactional
    public Long updateMenuType(Long id, MenuTypeRequestDto requestDto){
        var menuType = menuTypeRepository.findByIdAndActivatedTrue(id);

        if (!menuType.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }

        menuType.get().update(requestDto.getName());
        em.flush();
        em.clear();
        return menuType.get().getId();
    }

    @Transactional
    public void deactivateMenuType(Long id){
        var menuType = menuTypeRepository.findByIdAndActivatedTrue(id);

        if(!menuType.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }

        menuType.get().updateActivation(false);
        em.flush();
        em.clear();
        return;
    }

    @Transactional
    public void activateMenuType(Long id){
        var menuType = menuTypeRepository.findByIdAndActivatedFalse(id);

        if (!menuType.isPresent()){
            throw new NoResultException("해당하는 메뉴타입 정보가 없습니다.");
        }

        menuType.get().updateActivation(true);
        em.flush();
        em.clear();
        return;
    }
}
