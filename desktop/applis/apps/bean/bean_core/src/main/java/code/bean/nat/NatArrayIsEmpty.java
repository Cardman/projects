package code.bean.nat;

import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class NatArrayIsEmpty implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return BooleanStruct.of(((ArrayStruct)_instance).getLength()==0);
    }
}
