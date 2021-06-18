package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MenuDetailResponseDto {

    private Long id;

    private String name;

    private Integer cost;

    private String imagePath;

    @JsonProperty("menu_id")
    private Long menuId;

    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;

    public MenuDetailResponseDto(Long id, String name, Integer cost, String imagePath, Long menuId, LocalDateTime createdDate, LocalDateTime lastModifiedDate){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.imagePath = imagePath;
        this.menuId = menuId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public Integer getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Long getMenuId() {
        return menuId;
    }
}
