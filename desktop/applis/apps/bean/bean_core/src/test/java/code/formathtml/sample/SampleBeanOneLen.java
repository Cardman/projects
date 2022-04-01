package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleBeanOneLen implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(new IntStruct(NumParsers.getString(_args[0]).getInstance().length()));
    }
}
