package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public final class ScoresPlayersSum implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt((((ScoresPlayersStruct)_instance).getScoresPlayers()).getRate());
    }
}
