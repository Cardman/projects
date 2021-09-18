package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.nat.BeanStruct;

public final class NaNuIntGet implements NatCaller {
    @Override
    public Struct re(ContextEl _cont, Struct _instance, Struct[] _args) {
        BeanTwo i_ = (BeanTwo)((BeanStruct)_instance).getBean();
        if (i_.getNullableInt() == null) {
            return (NullStruct.NULL_VALUE);
        } else {
            return new LongStruct(i_.getNullableInt());
        }
    }
}
