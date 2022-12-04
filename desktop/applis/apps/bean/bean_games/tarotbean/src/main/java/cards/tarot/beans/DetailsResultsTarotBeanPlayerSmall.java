package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class DetailsResultsTarotBeanPlayerSmall implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance()).getPlayerSmall());
    }
}
