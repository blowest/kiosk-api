package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuDetail;
import blowest.kiosk.entity.status.ActivationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuDetailRequestDto {

    private String name;

    private Integer cost;

    private String imagePath;

    @JsonProperty("menu_id")
    private Long menuId;

    public String getName(){
        return name;
    }

    public Long getMenuId(){
        return  menuId;
    }

    public MenuDetail toEntity(Menu menu) {
        return MenuDetail.construct(name, cost, imagePath, ActivationStatus.ACTIVATED, menu);
    }

//    public void update(MenuDetail menuDetail, Menu menu){
//        menuDetail.setName(name);
//        menuDetail.setMenu(menu);
//    }

}
