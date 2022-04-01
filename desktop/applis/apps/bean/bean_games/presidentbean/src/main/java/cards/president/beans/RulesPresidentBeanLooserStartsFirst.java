package cards.president.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public class RulesPresidentBeanLooserStartsFirst implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        RulesPresidentBean rules_ = (RulesPresidentBean) ((PresidentBeanStruct)_instance).getInstance();
        return BooleanStruct.of(rules_.isLooserStartsFirst());
    }
}
