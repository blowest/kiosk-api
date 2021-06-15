package blowest.kiosk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuResponseDto {

    private Long id;

    @JsonProperty("image_path")
    private String imagePath;

    private boolean best;

    @JsonProperty("minimum_cost")
    private Integer minimumCost;

    public MenuResponseDto(Long id, String imagePath, boolean best, Integer minimumCost) {
        this.id = id;
        this.imagePath = imagePath;
        this.best = best;
        this.minimumCost = minimumCost;
    }

    public Long getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isBest() {
        return best;
    }

    public Integer getMinimumCost() {
        return minimumCost;
    }
}
