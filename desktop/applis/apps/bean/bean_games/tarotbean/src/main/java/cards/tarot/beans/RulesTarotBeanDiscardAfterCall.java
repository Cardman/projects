package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesTarotBeanDiscardAfterCall implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).isDiscardAfterCall());
    }
}
