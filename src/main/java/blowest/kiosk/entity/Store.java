package blowest.kiosk.entity;

import blowest.kiosk.entity.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(boolean activated) {
        this.activated =  activated;
    }
}
