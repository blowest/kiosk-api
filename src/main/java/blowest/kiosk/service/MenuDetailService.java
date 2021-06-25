package blowest.kiosk.service;

import blowest.kiosk.dto.MenuDetailRequestDto;
import blowest.kiosk.dto.MenuDetailResponseDto;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.repository.MenuDetailRepository;
import blowest.kiosk.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
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
        var menu = menuRepository.findById(requestDto.getMenuId());
        if (!menu.isPresent()){
            throw new NoResultException("해당하는 메뉴 정보가 없습니다.");
        }
        return menuDetailRepository.save(requestDto.toEntity(menu.get())).getId();
    }

    public List<MenuDetailResponseDto> retrieveAll(){
        var menuDetails = menuDetailRepository.findAllActivated();
        if (menuDetails.isEmpty()){
            throw new NoResultException("등록된 상세메뉴 정보가 없습니다.");
        }
        return menuDetails
                .stream()
                .map(x -> MenuDetailResponseDto.construct(x.getId(), x.getName(), x.getCost(), x.getImagePath(), x.getMenu().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    public MenuDetailResponseDto retrieve(Long id){
        var menuDetails = menuDetailRepository.findOneActivated(id);
        if (!menuDetails.isPresent()){
            throw new NoResultException("해당하는 상세메뉴 정보가 없습니다.");
        }
        return menuDetails
                .map(x-> MenuDetailResponseDto.construct(x.getId(), x.getName(), x.getCost(), x.getImagePath(), x.getMenu().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .orElse(null);
    }

    @Transactional
    public Long update(Long id, MenuDetailRequestDto requestDto){
        var menuRetrieved = menuRepository.findById(requestDto.getMenuId());
        if (!menuRetrieved.isPresent()){
            throw new NoResultException("해당하는 메뉴 정보가 없습니다.");
        }
        var menuDetailRetrieved = menuDetailRepository.findOneActivated(id);
        if (!menuDetailRetrieved.isPresent()){
            throw new NoResultException("해당하는 상세메뉴 정보가 없습니다.");
        }

        menuDetailRetrieved.get().update(requestDto.getName());
        menuDetailRetrieved.get().setMenu(menuRetrieved.get());

        return menuDetailRetrieved.get().getId();
    }

    @Transactional
    public Long deactivate(Long id){
        var menuDetail = menuDetailRepository.findOneActivated(id);
        if (!menuDetail.isPresent()){
            throw new NoResultException("해당하는 상세메뉴 정보가 없습니다.");
        }
        menuDetail.get().updateActivation(ActivationStatus.DEACTIVATED);

        return menuDetail.get().getId();
    }

    @Transactional
    public Long activate(Long id){
        var menuDetail = menuDetailRepository.findOneDeactivated(id);
        if (!menuDetail.isPresent()){
            throw new NoResultException("해당하는 상세메뉴 정보가 없습니다.");
        }
        menuDetail.get().updateActivation(ActivationStatus.ACTIVATED);

        return menuDetail.get().getId();
    }































}
