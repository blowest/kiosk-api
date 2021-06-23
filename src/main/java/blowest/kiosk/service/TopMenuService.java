package blowest.kiosk.service;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.dto.TopMenuResponseDto;
import blowest.kiosk.repository.StoreRepository;
import blowest.kiosk.repository.TopMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopMenuService {

    private final StoreRepository storeRepository;
    private final TopMenuRepository topMenuRepository;

    private final EntityManager em;

    public TopMenuService(StoreRepository storeRepository, TopMenuRepository topMenuRepository, EntityManager em) {
        this.storeRepository = storeRepository;
        this.topMenuRepository = topMenuRepository;
        this.em = em;
    }

    @Transactional
    public Long create(TopMenuRequestDto requestDto) {
        var store = storeRepository.findByIdAndActivatedTrue(requestDto.getStoreId());
        if (!store.isPresent()) {
            throw new NoResultException("해당하는 가게가 없습니다.");
        }
        return topMenuRepository.save(requestDto.toEntity(store.get())).getId();
    }

    @Transactional(readOnly = true)
    public List<TopMenuResponseDto> retrieveAll() {
        var topMenus = topMenuRepository.findAllByActivatedTrue();
        if (topMenus.isEmpty()){
            throw new NoResultException("등록된 상위메뉴 정보가 없습니다.");
        }
        return topMenus
                .stream()
                .map(x -> new TopMenuResponseDto(x.getId(), x.getName(), x.getStore().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TopMenuResponseDto retrieve(Long id) {
        var topMenus = topMenuRepository.findByIdAndActivatedTrue(id);
        if (!topMenus.isPresent()){
            throw new NoResultException("해당 상위메뉴가 없습니다.");
        }
        return topMenuRepository.findByIdAndActivatedTrue(id)
                .map(x -> new TopMenuResponseDto(x.getId(), x.getName(), x.getStore().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .orElse(null);
    }


    @Transactional
    public Long update(Long id, TopMenuRequestDto requestDto) {
        var storeRetrieved = storeRepository.findByIdAndActivatedTrue(requestDto.getStoreId());
        if (!storeRetrieved.isPresent()){
            throw new NoResultException("해당하는 가게가 없습니다.");
        }
        var topMenuRetrieved = topMenuRepository.findByIdAndActivatedTrue(id);
        if (!topMenuRetrieved.isPresent()){
            throw new NoResultException("해당 상위메뉴가 없습니다.");
        }

//        requestDto.update(topMenuRetrieved, storeRetrieved);
        topMenuRetrieved.get().update(requestDto.getName());
        topMenuRetrieved.get().setStore(storeRetrieved.get()); //.get()을 붙여야 되는데 이유를 모르겠습니다...
        em.flush();
        em.clear();

        return topMenuRetrieved.get().getId();
    }

    @Transactional
    public Long deactivate(Long id) {
        var topMenu = topMenuRepository.findByIdAndActivatedTrue(id);
        if (!topMenu.isPresent()){
            throw new NoResultException("해당하는 상위메뉴 정보가 없습니다.");
        }

        topMenu.get().updateActivation(false);
        em.flush();
        em.clear();

        return topMenu.get().getId();
    }

    @Transactional
    public Long activate(Long id) {
        var topMenu = topMenuRepository.findByIdAndActivatedFalse(id);
        if (!topMenu.isPresent()){
            throw new NoResultException("해당하는 상위메뉴 정보가 없습니다.");
        }
        topMenu.get().updateActivation(true);
        em.flush();
        em.clear();

        return topMenu.get().getId();
    }
}
