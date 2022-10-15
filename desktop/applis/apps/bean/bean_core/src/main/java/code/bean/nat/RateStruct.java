package code.bean.nat;

import code.expressionlanguage.structs.AbNullStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;

public final class RateStruct extends AbNullStruct implements NatDisplayableStruct {

    private final Rate value;
    public RateStruct(Rate _instance) {
        value = _instance;
    }
    public static Struct toStruct(Rate _instance) {
        if (_instance == null) {
            return NullStruct.NULL_VALUE;
        }
        return new RateStruct(_instance);
    }

    public static Rate convertToRate(Struct _r) {
        if (_r instanceof RateStruct) {
            return ((RateStruct)_r).getInstance();
        }
        return Rate.zero();
    }

    public Rate getInstance() {
        return value;
    }

    @Override
    public StringStruct getDisplayedString() {
        return new StringStruct(getInstance().toNumberString());
    }
}
