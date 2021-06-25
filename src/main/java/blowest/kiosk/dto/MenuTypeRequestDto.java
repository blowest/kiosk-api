package blowest.kiosk.dto;

import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.status.ActivationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuTypeRequestDto {

    private String name;

    public MenuType toEntity(){
        return MenuType.construct(name, ActivationStatus.ACTIVATED);
    }
}
