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

    public TopMenu toEntity(Store store) {
        return TopMenu.construct(name, ActivationStatus.ACTIVATED, store);
    }

}
