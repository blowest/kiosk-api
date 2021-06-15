package blowest.kiosk.dto;

public class MenuResponseDto {

    private Long id;

    private String imagePath;

    private boolean best;

    private Integer minimumCost;

    public MenuResponseDto(Long id, String imagePath, boolean best, Integer minimumCost) {
        this.id = id;
        this.imagePath = imagePath;
        this.best = best;
        this.minimumCost = minimumCost;
    }
}
