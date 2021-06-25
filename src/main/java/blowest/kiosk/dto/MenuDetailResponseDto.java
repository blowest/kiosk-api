package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuDetailResponseDto {

    private Long id;

    private String name;

    private Integer cost;

    private String imagePath;

    @JsonProperty("menu_id")
    private Long menuId;

    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;

    public static MenuDetailResponseDto construct(Long id, String name, Integer cost, String imagePath, Long menuId, LocalDateTime createdDate, LocalDateTime lastModifiedDate){
        var menuDetailResponseDto = new MenuDetailResponseDto();
        menuDetailResponseDto.id = id;
        menuDetailResponseDto.name = name;
        menuDetailResponseDto.cost = cost;
        menuDetailResponseDto.imagePath = imagePath;
        menuDetailResponseDto.menuId = menuId;
        menuDetailResponseDto.createdDate = createdDate;
        menuDetailResponseDto.lastModifiedDate = lastModifiedDate;

        return menuDetailResponseDto;
    }
}
