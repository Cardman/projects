package code.formathtml.sample;

import code.bean.nat.NatArrayStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class SampleBeanTwoTypedStringSet2 implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        ((NatArrayStruct)_instance).set(0,_args[0]);
//        ((SampleBeanStruct)_instance).setTypedString2(NumParsers.getString(_args[0]).getInstance());
        return _args[0];
    }
}
