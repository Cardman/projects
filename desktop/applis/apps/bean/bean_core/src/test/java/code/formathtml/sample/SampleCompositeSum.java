package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeSum implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return new LongStruct(NumParsers.convertToNumber(_args[0]).longStruct()+NumParsers.convertToNumber(_args[1]).longStruct());
    }

}
