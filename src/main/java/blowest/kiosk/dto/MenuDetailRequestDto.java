package blowest.kiosk.dto;

import blowest.kiosk.entity.Menu;
import blowest.kiosk.entity.MenuDetail;
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
        return new MenuDetail(name, cost, imagePath,true, menu);
    }

//    public void update(MenuDetail menuDetail, Menu menu){
//        menuDetail.setName(name);
//        menuDetail.setMenu(menu);
//    }

}
