package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.nat.BeanStruct;

public final class NaNuIntSet implements NatCaller {
    @Override
    public Struct re(ContextEl _cont, Struct _instance, Struct[] _args) {
        BeanTwo i_ = (BeanTwo)((BeanStruct)_instance).getBean();
        if (_args[0]== NullStruct.NULL_VALUE) {
            i_.setNullableInt(null);
            return(NullStruct.NULL_VALUE);
        }
        i_.setNullableInt(NumParsers.convertToNumber(_args[0]).longStruct());
        return(NullStruct.NULL_VALUE);
    }
}
