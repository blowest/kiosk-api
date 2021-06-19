package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;

import javax.persistence.*;

@Entity
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCost() {
        return cost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isActivated() {
        return activated;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.getMenuDetails().add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
