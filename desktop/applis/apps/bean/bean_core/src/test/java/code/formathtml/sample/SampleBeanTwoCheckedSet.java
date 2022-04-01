package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoCheckedSet implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        ((SampleBeanStruct)_instance).setChecked(BooleanStruct.isTrue(_args[0]));
        return(BooleanStruct.of(((SampleBeanStruct)_instance).isChecked()));
    }
}
