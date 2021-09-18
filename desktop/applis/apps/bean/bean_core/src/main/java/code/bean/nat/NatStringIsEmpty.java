package code.bean.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class NatStringIsEmpty implements NatCaller {
    @Override
    public Struct re(ContextEl _cont, Struct _instance, Struct[] _args) {
        return BooleanStruct.of(NumParsers.getString(_args[0]).getInstance().isEmpty());
    }
}
