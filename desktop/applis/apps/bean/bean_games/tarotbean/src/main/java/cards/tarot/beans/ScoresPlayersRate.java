package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
public final class ScoresPlayersRate implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RateStruct((((ScoresPlayersStruct)_instance).getScoresPlayers()).getRate());
    }
}
