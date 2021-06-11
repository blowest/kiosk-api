package blowest.kiosk.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StoreResponseDto {

    private Long id;

    private String name;

    private LocalDateTime createdTime;

    private LocalDateTime lastModifiedDate;

    public StoreResponseDto(Long id, String name, LocalDateTime createdTime, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
        this.lastModifiedDate = lastModifiedDate;
    }

    // Getter가 없으면 Json으로 변환되지 못함
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
}
