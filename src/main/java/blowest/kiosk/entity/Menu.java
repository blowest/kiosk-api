package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.entity.status.TierStatus;
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
    @Column(name = "menu_id")
    private Long id;

    private String imagePath;

    @Enumerated(EnumType.STRING)
    private TierStatus tierStatus; // true, false

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

    public static Menu construct(String imagePath, TierStatus tierStatus, Integer minimumCost, ActivationStatus activated, TopMenu topMenu, MenuType menuType) {
        var menu = new Menu();
        menu.imagePath = imagePath;
        menu.tierStatus = tierStatus;
        menu.minimumCost = minimumCost;
        menu.activationStatus = activated;
        menu.topMenu = topMenu;
        menu.menuType = menuType;

        return menu;
    }

    public void update(String imagePath, TierStatus tierStatus, Integer minimumCost, TopMenu topMenu, MenuType menuType) {
        this.imagePath = imagePath;
        this.tierStatus = tierStatus;
        this.minimumCost = minimumCost;
        setTopMenu(topMenu);
        setMenuType(menuType);
    }

    public void deactivate() {
        this.activationStatus = ActivationStatus.DEACTIVATED;
    }

    public void activate() {
        this.activationStatus = ActivationStatus.ACTIVATED;
    }

    private void setTopMenu(TopMenu topMenu) {
        this.topMenu = topMenu;
        topMenu.getMenus().add(this);
    }

    private void setMenuType(MenuType menuType) {
        this.menuType = menuType;
        menuType.getMenus().add(this);
    }
}
