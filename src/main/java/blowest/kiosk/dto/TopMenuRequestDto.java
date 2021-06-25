package blowest.kiosk.dto;

import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.TopMenu;
import blowest.kiosk.entity.status.ActivationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TopMenuRequestDto {

    private String name;

    @JsonProperty("store_id")
    private Long storeId;

    public TopMenu toEntity(Store store) {
        return TopMenu.construct(name, ActivationStatus.ACTIVATED, store);
    }

    public void update(TopMenu topMenu, Store store) {
        topMenu.update(name);
        topMenu.setStore(store);
    }
}
