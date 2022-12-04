package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class TarotSumDeclaringPlayerSum implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((TarotSumDeclaringPlayerStruct) _instance).getSumDeclaringPlayer()).getSum());
    }
}
