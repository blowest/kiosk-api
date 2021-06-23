package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Menu extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String imagePath;

    private boolean best;

    private Integer minimumCost;

    private boolean activated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_menu_id")
    private TopMenu topMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_type_id")
    private MenuType menuType;

    @OneToMany(mappedBy = "menu")
    private List<MenuDetail> menuDetails = new ArrayList<>();

    protected Menu() {
    }

    public Menu(String imagePath, boolean best, Integer minimumCost, boolean activated, TopMenu topMenu, MenuType menuType) {
        this.imagePath = imagePath;
        this.best = best;
        this.minimumCost = minimumCost;
        this.activated = activated;
        setMenuType(menuType);
        setTopMenu(topMenu);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public void setMinimumCost(Integer minimumCost) {
        this.minimumCost = minimumCost;
    }

    public void updateActivation(boolean activated) {
        this.activated = activated;
    }

    public void setTopMenu(TopMenu topMenu) {
        this.topMenu = topMenu;
        topMenu.getMenus().add(this);
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
        menuType.getMenus().add(this);
    }
}
