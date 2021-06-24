package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import blowest.kiosk.entity.status.ActivationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MenuDetail extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_detail_id")
    private Long id;

    private String name;

    private Integer cost;

    private String imagePath;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

//    public MenuDetail(String name, Integer cost, String imagePath, boolean activated, Menu menu){
//        this.name = name;
//        this.cost = cost;
//        this.imagePath = imagePath;
//        this.activated = activated;
//        this.setMenu(menu);
//    }

    public static MenuDetail construct(String name, Integer cost, String imagePath, ActivationStatus activationStatus, Menu menu){
        var menuDetail = new  MenuDetail();
        menuDetail.name = name;
        menuDetail.cost = cost;
        menuDetail.imagePath = imagePath;
        menuDetail.activationStatus = activationStatus;
        menuDetail.menu = menu;

        return menuDetail;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.getMenuDetails().add(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }
}
