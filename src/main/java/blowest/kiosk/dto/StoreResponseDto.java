package blowest.kiosk.dto;

import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StoreResponseDto {

    private Long id;

    private String name;

    private LocalDate createdTime;

    private LocalDateTime lastModifiedDate;

    public StoreResponseDto(Long id, String name, LocalDate createdTime, LocalDateTime lastModifiedDate) {
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

    public LocalDate getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
}
