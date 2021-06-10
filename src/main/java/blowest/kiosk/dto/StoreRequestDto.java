package blowest.kiosk.dto;


import blowest.kiosk.entity.Store;

public class StoreRequestDto {

    private String name;

    // Getter가 없으면 json에서 dto로 데이터를 읽어오지 못함
    public String getName() {
        return name;
    }

    public Store toEntity() {
        return new Store(name, true);
    }
}
