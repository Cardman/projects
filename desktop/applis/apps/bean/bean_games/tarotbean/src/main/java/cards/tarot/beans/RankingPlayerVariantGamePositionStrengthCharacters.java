package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RankingPlayerVariantGamePositionStrengthCharacters implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((RankingPlayerVariantGameStruct)_instance).getRankingPlayerVariantGame()).getPositionStrengthCharacters());
    }
}
