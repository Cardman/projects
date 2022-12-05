package aiki.beans;

import aiki.fight.util.TypesDuo;
import code.bean.nat.NaNuSt;

public final class TypesDuoStruct extends NaNuSt {
    private final TypesDuo inst;
    public TypesDuoStruct(TypesDuo _instance) {
        inst=(_instance);
    }
    public TypesDuo getTypesDuo() {
        return inst;
    }
}
