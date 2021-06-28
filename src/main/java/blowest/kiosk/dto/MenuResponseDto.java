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

    private String name;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    private TierStatus tierStatus;

    public static MenuResponseDto construct(Long id, String imagePath, String name, Integer cost, TierStatus tierStatus) {
        var menuResponseDto = new MenuResponseDto();
        menuResponseDto.id = id;
        menuResponseDto.imagePath = imagePath;
        menuResponseDto.name = name;
        menuResponseDto.cost = cost;
        menuResponseDto.tierStatus = tierStatus;

        return menuResponseDto;
    }
}
