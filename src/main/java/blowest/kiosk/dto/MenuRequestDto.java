package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.TopMenu;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

        return new Menu(menuRequestDto.getImagePath(),
                false,
                menuRequestDto.getMinimumCost(),
                true,
                topMenu,
                menuType);
    }
}