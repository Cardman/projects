package code.formathtml.sample;

import code.bean.nat.NatArrayStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeEmStrings implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        NatArrayStruct arr_ = new NatArrayStruct(2);
        arr_.set(0,new StringStruct(""));
        arr_.set(1,new StringStruct("SECOND"));
        return arr_;
    }
}
