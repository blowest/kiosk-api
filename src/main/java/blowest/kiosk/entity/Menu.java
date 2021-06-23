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
public class Menu extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String imagePath;

    private boolean best; // true, false

    private Integer minimumCost;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus; // true, false

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_menu_id")
    private TopMenu topMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_type_id")
    private MenuType menuType;

    @OneToMany(mappedBy = "menu")
    private List<MenuDetail> menuDetails = new ArrayList<>();

//    public Menu(String imagePath, boolean best, Integer minimumCost, boolean activated, TopMenu topMenu, MenuType menuType) {
//        this.imagePath = imagePath;
//        this.best = best;
//        this.minimumCost = minimumCost;
//        this.activated = activated;
//        setMenuType(menuType);
//        setTopMenu(topMenu);
//    }

    public static Menu createMenu(String imagePath, boolean best, Integer minimumCost, ActivationStatus activated, TopMenu topMenu, MenuType menuType) {
        var menu = new Menu();
        menu.imagePath = imagePath;
        menu.best = best;
        menu.minimumCost = minimumCost;
        menu.activationStatus = activated;
        menu.topMenu = topMenu;
        menu.menuType = menuType;

        return menu;
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

    public void updateActivation(ActivationStatus activated) {
        this.activationStatus = activated;
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
