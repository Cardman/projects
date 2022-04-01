package cards.president.beans;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class LineDealScores implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        PresidentLineDeal instance_ = ((PresidentLineDealStruct)_instance).getLineDeal();
        return BeanNatCommonLgNames.getLongArray(instance_.getScores());
    }
}
