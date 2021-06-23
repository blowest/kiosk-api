package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public String getImagePath() {
        return imagePath;
    }

    public boolean isBest() {
        return best;
    }

    public Integer getMinimumCost() {
        return minimumCost;
    }

    public Long getTopMenuId() {
        return topMenuId;
    }

    public Long getMenuTypeId() {
        return menuTypeId;
    }

    public Menu toEntity(MenuRequestDto menuRequestDto, TopMenu topMenu, MenuType menuType) {

        return Menu.construct(menuRequestDto.getImagePath(),
                false,
                menuRequestDto.getMinimumCost(),
                ActivationStatus.ACTIVATED,
                topMenu,
                menuType);
    }
}
