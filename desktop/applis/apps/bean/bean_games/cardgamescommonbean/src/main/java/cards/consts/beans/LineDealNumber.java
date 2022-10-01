package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class LineDealNumber implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        LineDeal instance_ = ((LineDealStruct)_instance).getLineDeal();
        return new IntStruct(instance_.getNumber());
    }
}