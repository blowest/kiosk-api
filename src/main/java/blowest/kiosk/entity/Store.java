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
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;

    @OneToMany(mappedBy = "store")
    private List<TopMenu> topMenus = new ArrayList<>();

//    public Store(String name, boolean activated) {
//        this.name = name;
//        this.activated = activated;
//    }

    public static Store createStore(String name, ActivationStatus activationStatus){
        var store = new Store();
        store.name = name;
        store.activationStatus = activationStatus;

        return store;
    }

    public void update(String name) {
        this.name = name;
    }

    public void updateActivation(ActivationStatus activationStatus) {
        this.activationStatus =  activationStatus;
    }
}
