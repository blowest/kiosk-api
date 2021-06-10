package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

@Entity
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
    private List<MenuDetail> menuDetails;

    protected Menu() {
    }
}
