package cards.president.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class RulesPresidentBeanCartesBattues implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        RulesPresidentBean rules_ = (RulesPresidentBean) ((PresidentBeanStruct)_instance).getInstance();
        return new StringStruct(rules_.getCartesBattues());
    }
}
