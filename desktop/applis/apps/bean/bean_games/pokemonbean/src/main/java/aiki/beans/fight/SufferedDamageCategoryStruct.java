package aiki.beans.fight;

import aiki.beans.facade.fight.SufferedDamageCategory;
import code.bean.nat.NaNuSt;

public final class SufferedDamageCategoryStruct extends NaNuSt {
    private final SufferedDamageCategory inst;
    public SufferedDamageCategoryStruct(SufferedDamageCategory _instance) {
        inst=(_instance);
    }
    public SufferedDamageCategory getSufferedDamageCategory() {
        return inst;
    }
}
