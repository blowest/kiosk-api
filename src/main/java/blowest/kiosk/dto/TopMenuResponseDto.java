package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TopMenuResponseDto {

    private Long id;

    private String name;

    private Long storeId;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public static TopMenuResponseDto construct(Long id, String name, Long storeId, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        var topMenuResponseDto = new TopMenuResponseDto();
        topMenuResponseDto.id = id;
        topMenuResponseDto.name = name;
        topMenuResponseDto.storeId = storeId;
        topMenuResponseDto.createdDate = createdDate;
        topMenuResponseDto.lastModifiedDate = lastModifiedDate;

        return topMenuResponseDto;
    }
}
