package cards.president.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class PresidentBeanLinesDeal implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return PresidentStandards.getLineDealArray(((PresidentBean)((PresidentBeanStruct)_instance).getInstance()).getLinesDeal());
    }
}
