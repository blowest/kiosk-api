package blowest.kiosk.dto;


import blowest.kiosk.entity.Store;
import blowest.kiosk.entity.status.ActivationStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter // Getter가 없으면 json에서 dto로 데이터를 읽어오지 못함
public class StoreRequestDto {

    private String name;

    public Store toEntity() {
        return Store.construct(name, ActivationStatus.ACTIVATED);
    }
}
