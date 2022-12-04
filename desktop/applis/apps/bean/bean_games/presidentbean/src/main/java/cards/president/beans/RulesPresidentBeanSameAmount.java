package cards.president.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class RulesPresidentBeanSameAmount implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
        return NaBoSt.of(((RulesPresidentBean)((PresidentBeanStruct)_instance).getInstance()).sameAmount());
    }
}
