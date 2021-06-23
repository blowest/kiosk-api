package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MenuDetail extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Integer cost;

    private String imagePath;

    private boolean activated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    protected MenuDetail(){
    }

    public MenuDetail(String name, Integer cost, String imagePath, boolean activated, Menu menu){
        this.name = name;
        this.cost = cost;
        this.imagePath = imagePath;
        this.activated = activated;
        this.setMenu(menu);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.getMenuDetails().add(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(boolean activated) {
        this.activated = activated;
    }
}
