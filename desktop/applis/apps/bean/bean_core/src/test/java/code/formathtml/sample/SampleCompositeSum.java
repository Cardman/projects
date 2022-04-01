package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeSum implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        int s_ = 0;
        for (Struct s: _args) {
            s_ += NumParsers.convertToNumber(s).intStruct();
        }
        return new IntStruct(s_);
    }
}
