package cards.president.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class RulesPresidentBeanLooserStartsFirst implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        RulesPresidentBean rules_ = (RulesPresidentBean) ((PresidentBeanStruct)_instance).getInstance();
        return NaBoSt.of(rules_.isLooserStartsFirst());
    }
}
