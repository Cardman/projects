package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoTypedString2 implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(new StringStruct( ((SampleBeanStruct)_instance).getTypedString2()));
    }
}
