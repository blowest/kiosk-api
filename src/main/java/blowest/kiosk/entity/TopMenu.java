package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Entity
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
    private List<Menu> menus;

    protected TopMenu() {
    }

    public TopMenu(String name, boolean activated, Store store) {
        this.name = name;
        this.activated = activated;
        this.setStore(store);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActivated() {
        return activated;
    }

    public Store getStore() {
        return store;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setStore(Store store) {
        this.store = store;
        store.getTopMenus().add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
