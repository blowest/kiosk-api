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
}
