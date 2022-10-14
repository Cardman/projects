package code.bean.nat;

import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class CstNatCaller implements NatCaller {

    private final String value;

    public CstNatCaller(String _v) {
        value = _v;
    }

    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return new StringStruct(value);
    }
}
