package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuRequestDto {

    @JsonProperty("image_path")
    private String imagePath;

    private boolean best;

    @JsonProperty("minimum_cost")
    private Integer minimumCost;

    @JsonProperty("top_menu_id")
    private Long topMenuId;

    @JsonProperty("menu_type_id")
    private Long menuTypeId;

    public Menu toEntity(MenuRequestDto menuRequestDto, TopMenu topMenu, MenuType menuType) {

        return Menu.construct(menuRequestDto.getImagePath(),
                false,
                menuRequestDto.getMinimumCost(),
                ActivationStatus.ACTIVATED,
                topMenu,
                menuType);
    }
}
