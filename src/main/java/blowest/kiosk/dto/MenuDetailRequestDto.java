package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuDetail;
import blowest.kiosk.entity.status.ActivationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuDetailRequestDto {

    private String name;

    private Integer cost;

    private String imagePath;

    @JsonProperty("menu_id")
    private Long menuId;

    public MenuDetail toEntity(Menu menu) {
        return MenuDetail.construct(name, cost, imagePath, ActivationStatus.ACTIVATED, menu);
    }
}
