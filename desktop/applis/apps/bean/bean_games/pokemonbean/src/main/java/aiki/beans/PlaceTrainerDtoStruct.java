package aiki.beans;

import aiki.beans.facade.solution.dto.PlaceTrainerDto;
import code.bean.nat.NaNuSt;

public final class PlaceTrainerDtoStruct extends NaNuSt {
    private final PlaceTrainerDto inst;
    public PlaceTrainerDtoStruct(PlaceTrainerDto _instance) {
        inst=(_instance);
    }
    public PlaceTrainerDto getPlaceTrainerDto() {
        return inst;
    }
}
