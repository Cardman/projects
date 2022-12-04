package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanDifferenceScoreTaker implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getDifferenceScoreTaker());
    }
}
