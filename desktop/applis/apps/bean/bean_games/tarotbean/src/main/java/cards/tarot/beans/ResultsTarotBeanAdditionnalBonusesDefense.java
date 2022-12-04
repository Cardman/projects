package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsTarotBeanAdditionnalBonusesDefense implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getAdditionnalBonusesDefense());
    }
}
