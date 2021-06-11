package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private boolean activated;

    @OneToMany(mappedBy = "store")
    private List<TopMenu> topMenus = new ArrayList<>();

    protected Store() {
    }

    public Store(String name, boolean activated) {
        this.name = name;
        this.activated = activated;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
