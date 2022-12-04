package cards.tarot.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ScoresPlayersNickname implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt((((ScoresPlayersStruct)_instance).getScoresPlayers()).getNickname());
    }
}
