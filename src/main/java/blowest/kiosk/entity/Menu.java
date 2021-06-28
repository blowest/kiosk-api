package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.entity.status.MenuType;
import blowest.kiosk.entity.status.TierStatus;
import lombok.AccessLevel;
import lombok.Builder;
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

    private String name;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private TierStatus tierStatus; // true, false

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus; // true, false

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_menu_id")
    private TopMenu topMenu;

    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    public static Menu construct(String imagePath, String name, Integer cost,
                                 TierStatus tierStatus, ActivationStatus activationStatus, TopMenu topMenu, MenuType menuType) {
        var menu = new Menu();
        menu.imagePath = imagePath;
        menu.name = name;
        menu.cost = cost;
        menu.tierStatus = tierStatus;
        menu.activationStatus = activationStatus;
        setTopMenu(menu, topMenu);
        menu.menuType = menuType;

        return menu;
    }

    public void update(String imagePath, String name, Integer cost, TierStatus tierStatus, TopMenu topMenu, MenuType menuType) {
        this.imagePath = imagePath;
        this.name = name;
        this.cost = cost;
        this.tierStatus = tierStatus;
        setTopMenu(topMenu);
        this.menuType = menuType;
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

    private static void setTopMenu(Menu menu, TopMenu topMenu) {
        menu.topMenu = topMenu;
        topMenu.getMenus().add(menu);
    }

}
