package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeInteger0 implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return new IntStruct(1);
    }
}
