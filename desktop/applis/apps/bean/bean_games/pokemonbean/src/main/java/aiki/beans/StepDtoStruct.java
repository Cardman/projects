package aiki.beans;

import aiki.beans.facade.solution.dto.StepDto;
import code.bean.nat.NaNuSt;

public final class StepDtoStruct extends NaNuSt {
    private final StepDto inst;
    public StepDtoStruct(StepDto _instance) {
        inst=(_instance);
    }
    public StepDto getStepDto() {
        return inst;
    }
}
