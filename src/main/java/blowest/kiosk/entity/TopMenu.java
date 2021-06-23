package blowest.kiosk.entity;

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

    public static TopMenu createTopMenu(String name, ActivationStatus activationStatus, Store store){
        var topmenu = new TopMenu();
        topmenu.name = name;
        topmenu.activationStatus = activationStatus;
        topmenu.store = store;

        return topmenu;
    }

    public void setStore(Store store) {
        this.store = store;
        store.getTopMenus().add(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }
}
