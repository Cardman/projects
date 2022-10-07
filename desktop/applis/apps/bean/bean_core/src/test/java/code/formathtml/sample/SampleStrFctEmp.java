package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class SampleStrFctEmp implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return new StringStruct("");
    }
}
