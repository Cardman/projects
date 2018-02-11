package aiki.beans.facade.solution.dto;

public final class PlaceTrainerDto {
    private String place;
    private String trainer;

    public PlaceTrainerDto(String _place, String _trainer) {
        place = _place;
        trainer = _trainer;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getPlace() {
        return place;
    }
}