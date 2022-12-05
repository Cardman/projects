package aiki.beans;

import aiki.beans.effects.ComboDto;
import code.bean.nat.NaNuSt;

public final class ComboDtoStruct extends NaNuSt {
    private final ComboDto inst;
    public ComboDtoStruct(ComboDto _instance) {
        inst=(_instance);
    }
    public ComboDto getComboDto() {
        return inst;
    }
}
