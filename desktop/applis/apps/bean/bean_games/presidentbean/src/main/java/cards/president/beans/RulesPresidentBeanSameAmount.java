package cards.president.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public class RulesPresidentBeanSameAmount implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return BooleanStruct.of(((RulesPresidentBean)((PresidentBeanStruct)_instance).getInstance()).sameAmount());
    }
}
