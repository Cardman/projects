package code.bean.nat;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class NatArrayIsEmpty implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return BooleanStruct.of(((NatArrayStruct)_instance).getLength()==0);
    }
}
