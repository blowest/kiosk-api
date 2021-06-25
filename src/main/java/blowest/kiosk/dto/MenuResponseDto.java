package blowest.kiosk.dto;

import blowest.kiosk.entity.status.TierStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuResponseDto {

    private Long id;

    @JsonProperty("image_path")
    private String imagePath;

    @Enumerated(EnumType.STRING)
    private TierStatus tierStatus;

    @JsonProperty("minimum_cost")
    private Integer minimumCost;

    public static MenuResponseDto construct(Long id, String imagePath, TierStatus tierStatus, Integer minimumCost) {
        var menuResponseDto = new MenuResponseDto();
        menuResponseDto.id = id;
        menuResponseDto.imagePath = imagePath;
        menuResponseDto.tierStatus = tierStatus;
        menuResponseDto.minimumCost = minimumCost;

        return menuResponseDto;
    }
}
