package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class TarotBeanPlayClassicGame implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((TarotBeanStruct)_instance).getInstance().playClassicGame());
    }
}
