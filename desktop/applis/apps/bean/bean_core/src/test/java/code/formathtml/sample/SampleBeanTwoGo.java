package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoGo implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(new StringStruct(((SampleBeanStruct)_instance).go()));
    }
}
