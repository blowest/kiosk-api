package blowest.kiosk.dto;

import blowest.kiosk.entity.status.TierStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuPagedResponseDto {

    private long totalPages;

    private long totalMenus;

    List<MenuResponseDto> menus;

    public static MenuPagedResponseDto create(long totalPages, long totalMenus, List<MenuResponseDto> menus) {
        MenuPagedResponseDto dto = new MenuPagedResponseDto();
        dto.totalPages = totalPages;
        dto.totalMenus = totalMenus;
        dto.menus = menus;

        return dto;
    }
}
