package code.formathtml.sample;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeSum implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return BeanNatCommonLgNames.sum(_args);
    }

}
