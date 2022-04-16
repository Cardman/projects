package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeIntegerSet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        BeanThree i_ = (BeanThree) ((SampleBeanStruct)_instance).getBean();
        i_.setInteger(NumParsers.convertToNumber(_args[0]).intStruct());
        return new IntStruct(i_.getInteger());
    }
}
