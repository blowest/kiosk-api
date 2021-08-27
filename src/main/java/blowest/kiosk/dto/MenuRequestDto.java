package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
import blowest.kiosk.entity.status.MenuType;
import blowest.kiosk.entity.status.TierStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuRequestDto {

    private String imagePath;

    private String name;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private TierStatus tierStatus;

    private Integer minimumCost;

    private MenuType menuType;

    private Long topMenuId;

    public Menu toEntity(TopMenu topMenu) {
        return Menu.create(this.imagePath,
                this.name,
                this.cost,
                this.tierStatus,
                ActivationStatus.ACTIVATED,
                topMenu,
                this.menuType);
    }
}
