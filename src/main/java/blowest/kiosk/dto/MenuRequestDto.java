package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
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

    @JsonProperty("image_path")
    private String imagePath;

    private String name;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private TierStatus tierStatus;

    @JsonProperty("minimum_cost")
    private Integer minimumCost;

    @JsonProperty("top_menu_id")
    private Long topMenuId;

    @JsonProperty("menu_type_id")
    private Long menuTypeId;

    public Menu toEntity(TopMenu topMenu, MenuType menuType) {
        return Menu.construct(this.imagePath,
                this.name,
                this.cost,
                this.tierStatus,
                ActivationStatus.ACTIVATED,
                topMenu,
                menuType);
    }
}
