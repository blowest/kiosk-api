package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MenuType extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private boolean activated;

    @OneToMany(mappedBy = "menuType")
    private List<Menu> menus;


}