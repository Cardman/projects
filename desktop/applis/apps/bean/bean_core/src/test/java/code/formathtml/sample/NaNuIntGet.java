package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;

public final class NaNuIntGet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        SampleBeanStruct i_ = ((SampleBeanStruct)_instance);
        return RateStruct.toStruct(i_.getNullableInt());
//        if (i_.getNullableInt() == null) {
//            return (NullStruct.NULL_VALUE);
//        } else {
//            return new RateStruct(i_.getNullableInt(), BeanNatCommonLgNames.TYPE_RATE);
//        }
    }
}
