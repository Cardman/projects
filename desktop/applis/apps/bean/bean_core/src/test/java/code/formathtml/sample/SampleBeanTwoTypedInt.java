package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoTypedInt implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(new IntStruct(((SampleBeanStruct)_instance).getTypedInt()));
    }
}