package blowest.kiosk.dto;

import blowest.kiosk.entity.MenuType;

public class MenuTypeRequestDto {

    private String name;

    public String getName() {
        return name;
    }

    public MenuType toEntity(){
        return new MenuType(name,true);
    }
}
