package blowest.kiosk.service;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.dto.TopMenuResponseDto;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.repository.StoreRepository;
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
public class TopMenuService {

    private final StoreRepository storeRepository;
    private final TopMenuRepository topMenuRepository;

    @Transactional
    public Long create(TopMenuRequestDto requestDto) {
        var store = storeRepository.findOneActivated(1L)
                .orElseThrow(() -> new NoResultException("해당하는 가게 정보가 없습니다."));

        return topMenuRepository.save(requestDto.toEntity(store)).getId();
    }

    @Transactional
    public List<Long> createTopMenus(List<TopMenuRequestDto> requestDto) {
        var store = storeRepository.findOneActivated(1L)
                .orElseThrow(() -> new NoResultException("해당하는 가게 정보가 없습니다."));

        var topMenus= requestDto.stream().map(x -> x.toEntity(store)).collect(Collectors.toList());
        topMenuRepository.saveAll(topMenus);

        return topMenus.stream().map(TopMenu::getId).collect(Collectors.toList());
    }

    public List<TopMenuResponseDto> retrieveAll() {
        var topMenus = topMenuRepository.findAllActivated();
        if (topMenus.isEmpty()){
            throw new NoResultException("등록된 상위메뉴 정보가 없습니다.");
        }
        return topMenus
                .stream()
                .map(x -> TopMenuResponseDto.construct(x.getId(), x.getName(), x.getStore().getId(), x.getCreatedDate(), x.getLastModifiedDate()))
                .collect(Collectors.toList());
    }

    public TopMenuResponseDto retrieve(Long id) {
        var topMenu = topMenuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당 상위메뉴가 없습니다."));

        return TopMenuResponseDto.construct(topMenu.getId(), topMenu.getName(), topMenu.getStore().getId(), topMenu.getCreatedDate(), topMenu.getLastModifiedDate());

    }


    @Transactional
    public Long update(Long id, TopMenuRequestDto requestDto) {
        var store = storeRepository.findOneActivated(1L)
                .orElseThrow(() -> new NoResultException("해당하는 가게가 없습니다."));
        var topMenu = topMenuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당 상위메뉴가 없습니다."));

        topMenu.update(requestDto, store);
//        topMenuRetrieved.get().update(requestDto.getName());
//        topMenuRetrieved.get().setStore(storeRetrieved.get()); //.get()을 붙여야 되는데 이유를 모르겠습니다... -> Optional<T>로 반환될때는 get()을 붙여줘야 해당되는 객체에 접근가능합니다.

        return topMenu.getId();
    }

    @Transactional
    public Long deactivate(Long id) {
        var topMenu = topMenuRepository.findOneActivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 상위메뉴 정보가 없습니다."));
        topMenu.deactivate();

        return topMenu.getId();
    }

    @Transactional
    public Long activate(Long id) {
        var topMenu = topMenuRepository.findOneDeactivated(id)
                .orElseThrow(() -> new NoResultException("해당하는 상위메뉴 정보가 없습니다."));
        topMenu.activate();

        return topMenu.getId();
    }
}
