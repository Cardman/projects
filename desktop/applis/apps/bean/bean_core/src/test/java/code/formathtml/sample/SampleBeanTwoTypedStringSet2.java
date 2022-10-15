package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoTypedStringSet2 implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        ((SampleBeanStruct)_instance).setTypedString2(NumParsers.getString(_args[0]).getInstance());
        return(new StringStruct( ((SampleBeanStruct)_instance).getTypedString2()));
    }
}
