package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsTarotBeanSuccessfulDeclaredSlamAttack implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).successfulDeclaredSlamAttack());
    }
}
