package aiki.beans;

import aiki.fight.util.TypeDamageBoost;
import code.bean.nat.NaNuSt;

public final class TypeDamageBoostStruct extends NaNuSt {
    private final TypeDamageBoost inst;
    public TypeDamageBoostStruct(TypeDamageBoost _instance) {
        inst=(_instance);
    }
    public TypeDamageBoost getTypeDamageBoost() {
        return inst;
    }
}
