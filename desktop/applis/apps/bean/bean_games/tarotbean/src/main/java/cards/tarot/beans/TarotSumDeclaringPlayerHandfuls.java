package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public final class TarotSumDeclaringPlayerHandfuls implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return TarotStandards.getStringShortTree((((TarotSumDeclaringPlayerStruct) _instance).getSumDeclaringPlayer()).getHandfuls());
    }
}
