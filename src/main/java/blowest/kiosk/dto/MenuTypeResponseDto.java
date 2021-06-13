package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MenuTypeResponseDto {

    private Long id;

    private String name;

    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonProperty("modifided_date")
    private LocalDateTime modifiedDate;

    public MenuTypeResponseDto(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
