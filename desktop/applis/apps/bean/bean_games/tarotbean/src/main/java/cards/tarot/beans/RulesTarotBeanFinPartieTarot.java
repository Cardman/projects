package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesTarotBeanFinPartieTarot implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((RulesTarotBean) ((TarotBeanStruct)_instance).getInstance()).getFinPartieTarot());
    }
}
