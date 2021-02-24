package aiki.fight.util;

import code.maths.Rate;

public final class TypesDuoRate {

    private final TypesDuo stat;
    private final Rate value;

    public TypesDuoRate(TypesDuo _stat, Rate _value) {
        this.stat = _stat;
        this.value = _value;
    }

    public TypesDuo getStat() {
        return stat;
    }

    public Rate getValue() {
        return value;
    }
}
