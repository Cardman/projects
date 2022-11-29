package code.formathtml.sample;

import code.bean.nat.NatArrayStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeEmsStrings implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return new NatArrayStruct(0);
    }
}
