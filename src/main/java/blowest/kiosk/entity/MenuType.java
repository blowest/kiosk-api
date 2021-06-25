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
public class MenuType extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_type_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;

    @OneToMany(mappedBy = "menuType")
    private List<Menu> menus = new ArrayList<>();

//    public MenuType(String name, boolean activated){
//        this.name = name;
//        this.activated = activated;
//    }

    public static MenuType construct(String name, ActivationStatus activationStatus){
        var menuType = new MenuType();
        menuType.name = name;
        menuType.activationStatus = activationStatus;

        return menuType;
    }

    public void update(String name) {
        this.name = name;
    }

    public void deactivate() {
        this.activationStatus = ActivationStatus.DEACTIVATED;
    }

    public void activate() {
        this.activationStatus = ActivationStatus.ACTIVATED;
    }
}