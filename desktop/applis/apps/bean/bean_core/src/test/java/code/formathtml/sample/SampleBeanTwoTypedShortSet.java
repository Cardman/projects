package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoTypedShortSet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        ((SampleBeanStruct)_instance).setTypedShort(NumParsers.convertToNumber(_args[0]).shortStruct());
        return(new IntStruct(((SampleBeanStruct)_instance).getTypedShort()));
    }
}
