package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsTarotBeanFailedSlamAttack implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).failedSlamAttack());
    }
}
