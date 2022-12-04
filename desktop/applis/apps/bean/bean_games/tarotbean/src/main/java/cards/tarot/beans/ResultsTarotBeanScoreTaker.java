package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsTarotBeanScoreTaker implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getScoreTaker());
    }
}
