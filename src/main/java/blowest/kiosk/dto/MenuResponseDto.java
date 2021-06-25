package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuResponseDto {

    private Long id;

    @JsonProperty("image_path")
    private String imagePath;

    private boolean best;

    @JsonProperty("minimum_cost")
    private Integer minimumCost;

    public static MenuResponseDto construct(Long id, String imagePath, boolean best, Integer minimumCost) {
        var menuResponseDto = new MenuResponseDto();
        menuResponseDto.id = id;
        menuResponseDto.imagePath = imagePath;
        menuResponseDto.best = best;
        menuResponseDto.minimumCost = minimumCost;

        return menuResponseDto;
    }
}
