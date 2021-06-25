package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StoreResponseDto {

    private Long id;

    private String name;

    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;

    public static StoreResponseDto construct(Long id, String name, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        var storeResponseDto = new StoreResponseDto();
        storeResponseDto.id = id;
        storeResponseDto.name = name;
        storeResponseDto.createdDate = createdDate;
        storeResponseDto.lastModifiedDate = lastModifiedDate;
        return storeResponseDto;
    }
}
