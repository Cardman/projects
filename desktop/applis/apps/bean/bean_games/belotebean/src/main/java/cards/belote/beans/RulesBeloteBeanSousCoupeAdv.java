package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesBeloteBeanSousCoupeAdv implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((RulesBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).isSousCoupeAdv());
    }
}
