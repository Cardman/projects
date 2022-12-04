package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RankingPlayerVariantGamePositionCharacters implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((RankingPlayerVariantGameStruct)_instance).getRankingPlayerVariantGame()).getPositionCharacters());
    }
}
