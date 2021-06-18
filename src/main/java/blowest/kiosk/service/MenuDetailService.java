package blowest.kiosk.service;

import blowest.kiosk.dto.MenuDetailRequestDto;
import blowest.kiosk.dto.MenuDetailResponseDto;
import blowest.kiosk.repository.MenuDetailRepository;
import blowest.kiosk.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuDetailService {

    private final MenuRepository menuRepository;
    private final MenuDetailRepository menuDetailRepository;
    private final EntityManager em;

    public MenuDetailService(MenuDetailRepository menuDetailRepository, MenuRepository menuRepository, EntityManager em){
        this.menuDetailRepository = menuDetailRepository;
        this.menuRepository = menuRepository;
        this.em = em;
    }

    @Transactional
    public Long create(MenuDetailRequestDto requestDto){
        var menu = menuRepository.findByIdAndActivatedTrue(requestDto.getMenuId());
        if (!menu.isPresent()){
            return null;
        }
        return menuDetailRepository.save(requestDto.toEntity(menu.get())).getId();
    }

    @Transactional(readOnly = true)
    public List<MenuDetailResponseDto> retrieveAll(){
        return menuDetailRepository.findAllByActivatedTrue()
                .stream()
                .map(x -> new MenuDetailResponseDto(x.getId(), x.getName(), x.getCost(), x.getImagePath(), x.getMenu().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuDetailResponseDto retrieve(Long id){
        return menuDetailRepository.findByIdAndActivatedTrue(id)
                .map(x-> new MenuDetailResponseDto(x.getId(), x.getName(), x.getCost(), x.getImagePath(), x.getMenu().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .orElse(null);
    }

    @Transactional
    public Long update(Long id, MenuDetailRequestDto requestDto){
        var menuRetrieved = menuRepository.findByIdAndActivatedTrue(requestDto.getMenuId()).orElse(null);
        var menuDetailRetrieved = menuDetailRepository.findByIdAndActivatedTrue(id).orElse(null);

        menuDetailRetrieved.setName(requestDto.getName());
        menuDetailRetrieved.setMenu(menuRetrieved);
        em.flush();
        em.clear();

        return menuDetailRetrieved.getId();
    }

    @Transactional
    public Long deactivate(Long id){
        var menuDetail = menuDetailRepository.findByIdAndActivatedTrue(id).orElse(null);
        menuDetail.setActivated(false);
        em.flush();
        em.clear();

        return menuDetail.getId();
    }

    @Transactional
    public Long activate(Long id){
        var menuDetail = menuDetailRepository.findByIdAndActivatedFalse(id).orElse(null);
        menuDetail.setActivated(true);
        em.flush();
        em.clear();

        return menuDetail.getId();
    }































}
