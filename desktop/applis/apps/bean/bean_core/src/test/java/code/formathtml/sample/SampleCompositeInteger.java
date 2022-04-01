package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeInteger implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        BeanThree i_ = (BeanThree) ((SampleBeanStruct)_instance).getBean();
        return new IntStruct(i_.getInteger());
    }
}
