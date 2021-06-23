package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MenuType extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_type_id")
    private Long id;

    private String name;

    private boolean activated;

    @OneToMany(mappedBy = "menuType")
    private List<Menu> menus = new ArrayList<>();

    protected MenuType(){
    }

    public MenuType(String name, boolean activated){
        this.name = name;
        this.activated = activated;
    }

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(boolean activated) {
        this.activated = activated;
    }
}