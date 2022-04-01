package cards.president.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class LineDealNumber implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        PresidentLineDeal instance_ = ((PresidentLineDealStruct)_instance).getLineDeal();
        return new IntStruct(instance_.getNumber());
    }
}
