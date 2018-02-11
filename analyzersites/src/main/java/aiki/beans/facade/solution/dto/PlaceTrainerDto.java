package aiki.beans.facade.solution.dto;
import code.bean.Accessible;

public final class PlaceTrainerDto {

    @Accessible
    private String place;

    @Accessible
    private String trainer;

    public PlaceTrainerDto(String _place, String _trainer) {
        place = _place;
        trainer = _trainer;
    }
}
