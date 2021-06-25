package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuTypeResponseDto {

    private Long id;

    private String name;

    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonProperty("modified_date")
    private LocalDateTime modifiedDate;

    public static MenuTypeResponseDto construct(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate){
        var menuTypeResponseDto = new MenuTypeResponseDto();
        menuTypeResponseDto.id = id;
        menuTypeResponseDto.name = name;
        menuTypeResponseDto.createdDate = createdDate;
        menuTypeResponseDto.modifiedDate = modifiedDate;

        return menuTypeResponseDto;
    }
}
