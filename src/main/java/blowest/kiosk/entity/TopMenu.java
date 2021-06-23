package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class TopMenu extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private boolean activated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "topMenu")
    private List<Menu> menus = new ArrayList<>();

    protected TopMenu() {
    }

    public TopMenu(String name, boolean activated, Store store) {
        this.name = name;
        this.activated = activated;
        this.setStore(store);
    }

    public void setStore(Store store) {
        this.store = store;
        store.getTopMenus().add(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(boolean activated) {
        this.activated = activated;
    }
}
