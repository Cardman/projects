package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ScoresPlayersScore implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt((((ScoresPlayersStruct)_instance).getScoresPlayers()).getScore());
    }
}
