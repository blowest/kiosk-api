package blowest.kiosk.service;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.dto.TopMenuResponseDto;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.repository.StoreRepository;
import blowest.kiosk.repository.TopMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
            return null;
        }
        return topMenuRepository.save(requestDto.toEntity(store.get())).getId();
    }

    @Transactional(readOnly = true)
    public List<TopMenuResponseDto> retrieveAll() {
        return topMenuRepository.findAllByActivatedTrue()
                .stream()
                .map(x -> new TopMenuResponseDto(x.getId(), x.getName(), x.getStore().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TopMenuResponseDto retrieve(Long id) {
        return topMenuRepository.findByIdAndActivatedTrue(id)
                .map(x -> new TopMenuResponseDto(x.getId(), x.getName(), x.getStore().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .orElse(null);
    }


    @Transactional
    public Long update(Long id, TopMenuRequestDto requestDto) {
        var storeRetrieved = storeRepository.findByIdAndActivatedTrue(requestDto.getStoreId()).orElse(null);
        var topMenuRetrieved = topMenuRepository.findByIdAndActivatedTrue(id).orElse(null);

//        requestDto.update(topMenuRetrieved, storeRetrieved);
        topMenuRetrieved.setName(requestDto.getName());
        topMenuRetrieved.setStore(storeRetrieved);
        em.flush();
        em.clear();

        return topMenuRetrieved.getId();
    }

    @Transactional
    public Long deactivate(Long id) {
        var topMenu = topMenuRepository.findByIdAndActivatedTrue(id).orElse(null);
        topMenu.setActivated(false);
        em.flush();
        em.clear();

        return topMenu.getId();
    }

    @Transactional
    public Long activate(Long id) {
        var topMenu = topMenuRepository.findByIdAndActivatedFalse(id).orElse(null);
        topMenu.setActivated(true);
        em.flush();
        em.clear();

        return topMenu.getId();
    }
}
