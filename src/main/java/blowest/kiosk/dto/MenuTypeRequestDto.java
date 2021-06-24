package blowest.kiosk.dto;

import blowest.kiosk.entity.MenuType;
import blowest.kiosk.entity.status.ActivationStatus;

public class MenuTypeRequestDto {

    private String name;

    public String getName() {
        return name;
    }

    public MenuType toEntity(){
        return MenuType.construct(name, ActivationStatus.ACTIVATED);
    }
}
