package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.BeanStruct;

public final class NaNuIntSet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        BeanStruct i_ = ((BeanStruct)_instance);
        if (_args[0]== NullStruct.NULL_VALUE) {
            i_.setNullableInt(RateStruct.convertToRate(null));
            return(NullStruct.NULL_VALUE);
        }
        i_.setNullableInt(RateStruct.convertToRate(_args[0]));
        return(NullStruct.NULL_VALUE);
    }
}
