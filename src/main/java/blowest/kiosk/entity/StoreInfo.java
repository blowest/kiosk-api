package blowest.kiosk.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class StoreInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Boolean isActive;

    protected StoreInfo() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }
}
