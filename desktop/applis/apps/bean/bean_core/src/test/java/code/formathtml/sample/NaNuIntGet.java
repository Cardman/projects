package code.formathtml.sample;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.BeanStruct;

public final class NaNuIntGet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        BeanStruct i_ = ((BeanStruct)_instance);
        if (i_.getNullableInt() == null) {
            return (NullStruct.NULL_VALUE);
        } else {
            return new RateStruct(i_.getNullableInt(), BeanNatCommonLgNames.TYPE_RATE);
        }
    }
}
