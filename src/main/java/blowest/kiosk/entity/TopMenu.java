package blowest.kiosk.entity;

import blowest.kiosk.dto.TopMenuRequestDto;
import blowest.kiosk.entity.base.BaseTimeEntity;
import blowest.kiosk.entity.status.ActivationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TopMenu extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "top_menu_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "topMenu")
    private List<Menu> menus = new ArrayList<>();

//    public TopMenu(String name, boolean activated, Store store) {
//        this.name = name;
//        this.activated = activated;
//        this.setStore(store);
//    }

    public static TopMenu construct(String name, ActivationStatus activationStatus, Store store){
        var topMenu = new TopMenu();
        topMenu.name = name;
        topMenu.activationStatus = activationStatus;
        topMenu.store = store;

        return topMenu;
    }

    private void setStore(Store store) {
        this.store = store;
        store.getTopMenus().add(this);
    }

    public void deactivate() {
        this.activationStatus = ActivationStatus.DEACTIVATED;
    }

    public void activate() {
        this.activationStatus = ActivationStatus.ACTIVATED;
    }

    public void update(TopMenuRequestDto requestDto, Store store) {
        name = requestDto.getName();
        setStore(store);
    }
}
