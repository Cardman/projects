package cards.president.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class RulesPresidentBeanNbCardsPerPlayerMax implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        RulesPresidentBean rules_ = (RulesPresidentBean) ((PresidentBeanStruct)_instance).getInstance();
        return new IntStruct(rules_.getNbCardsPerPlayerMax());
    }
}
