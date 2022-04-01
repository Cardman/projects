package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoChecked implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(BooleanStruct.of(((SampleBeanStruct)_instance).isChecked()));
    }
}
